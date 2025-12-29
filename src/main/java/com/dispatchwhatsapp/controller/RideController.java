package com.dispatchwhatsapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dispatchwhatsapp.model.Ride;
import com.dispatchwhatsapp.service.RideService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ride")
public class RideController {

    private final RideService service;

    // ⭐ Now supports BOTH Browser GET and POST
    @RequestMapping(value = "/book", method = { RequestMethod.POST, RequestMethod.GET })
    public Ride book(@RequestParam String name,
                     @RequestParam String phone,
                     @RequestParam int pickup,
                     @RequestParam int drop) {
        return service.bookRide(name, phone, pickup, drop);
    }

    @RequestMapping(value = "/verify", method = { RequestMethod.POST, RequestMethod.GET })
    public String verify(@RequestParam Long rideId,
                         @RequestParam String otp) {
        boolean ok = service.verifyOtp(rideId, otp);
        return ok ? "OTP verified, ride started!" : "Invalid OTP";
    }

    @RequestMapping(value = "/complete", method = { RequestMethod.POST, RequestMethod.GET })
    public String complete(@RequestParam Long rideId,
                           @RequestParam int rating) {
        service.completeRide(rideId, rating);
        return "Ride completed & rating saved";
    }
}

