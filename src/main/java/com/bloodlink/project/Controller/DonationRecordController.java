package com.bloodlink.project.Controller;

import com.bloodlink.project.Model.DonationRecord;
import com.bloodlink.project.Service.DonationRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donations")
public class DonationRecordController {

    private final DonationRecordService donationRecordService;

    public DonationRecordController(DonationRecordService donationRecordService) {
        this.donationRecordService = donationRecordService;
    }

    @PostMapping
    public ResponseEntity<DonationRecord> recordDonation(
            @RequestBody DonationRecord record) {

        return ResponseEntity.ok(
                donationRecordService.recordDonation(record)
        );
    }

    @GetMapping
    public ResponseEntity<List<DonationRecord>> getAllDonations() {

        return ResponseEntity.ok(
                donationRecordService.getAllDonations()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DonationRecord> getDonationById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                donationRecordService.getDonationById(id)
        );
    }

    @GetMapping("/donor/{donorId}")
    public ResponseEntity<List<DonationRecord>> getDonationsByDonor(
            @PathVariable Long donorId) {

        return ResponseEntity.ok(
                donationRecordService.getDonationsByDonor(donorId)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDonation(
            @PathVariable Long id) {

        donationRecordService.deleteDonation(id);

        return ResponseEntity.ok(
                "Donation record deleted successfully"
        );
    }
}