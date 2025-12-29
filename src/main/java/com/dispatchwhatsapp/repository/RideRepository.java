package com.dispatchwhatsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dispatchwhatsapp.model.Ride;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> { }


