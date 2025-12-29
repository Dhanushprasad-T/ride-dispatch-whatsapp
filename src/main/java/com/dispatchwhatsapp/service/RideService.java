package com.dispatchwhatsapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dispatchwhatsapp.model.Driver;
import com.dispatchwhatsapp.model.Otp;
import com.dispatchwhatsapp.model.Ride;
import com.dispatchwhatsapp.model.User;
import com.dispatchwhatsapp.repository.DriverRepository;
import com.dispatchwhatsapp.repository.OtpRepository;
import com.dispatchwhatsapp.repository.RideRepository;
import com.dispatchwhatsapp.repository.UserRepository;
import com.dispatchwhatsapp.util.OtpGenerator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor   // Lombok generates constructor for final fields
public class RideService {

    private final DriverRepository driverRepo;
    private final UserRepository userRepo;
    private final RideRepository rideRepo;
    private final OtpRepository otpRepo;

    public Ride bookRide(String name, String phone, int pickup, int drop) {

        User user = userRepo.findByPhone(phone);
        if (user == null) {
            user = User.builder().name(name).phone(phone).build();
            userRepo.save(user);
        }

        // get first free available driver
        List<Driver> drivers = driverRepo.findAll();
        Driver selected = null;
        for (Driver d : drivers) {
            if (d.isAvailable()) {
                selected = d;
                break;
            }
        }
        if (selected == null) return null;

        selected.setAvailable(false);
        driverRepo.save(selected);

        int fare = Math.abs(drop - pickup) * 10;

        Ride ride = Ride.builder()
                .pickup(pickup)
                .dropLocation(drop)
                .fare(fare)
                .status("OTP_PENDING")
                .driver(selected)
                .user(user)
                .build();
        rideRepo.save(ride);

        String otp = OtpGenerator.generateOtp();
        otpRepo.save(Otp.builder()
                .code(otp)
                .phone(phone)
                .rideId(ride.getId())
                .build());

        return ride;
    }

    public boolean verifyOtp(Long rideId, String code) {
        List<Otp> list = otpRepo.findAll();
        for (Otp o : list) {
            if (o.getRideId().equals(rideId) && o.getCode().equals(code))
                return true;
        }
        return false;
    }

    public void completeRide(Long rideId, int rating) {
        Ride r = rideRepo.findById(rideId).orElse(null);
        if (r != null) {
            r.setStatus("COMPLETED");
            rideRepo.save(r);

            Driver d = r.getDriver();
            d.setAvailable(true);
            driverRepo.save(d);
        }
    }
}


