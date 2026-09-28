package com.bloodlink.project.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DonorDTO {

    private Long id;

    private String name;

    private String phone;

    private String bloodGroup;

    private String city;

    private LocalDate lastDonationDate;

    private boolean available;
}