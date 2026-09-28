package com.bloodlink.project.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DonationRecordDTO {

    private Long id;

    private Long donorId;

    private LocalDate donationDate;

    private String hospitalName;

    private String city;
}