package com.dispatchwhatsapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int pickup;
    private int dropLocation;
    private int fare;
    private String status;

    @ManyToOne
    private Driver driver;

    @ManyToOne
    private User user;
}



