package com.bloodlink.project.Controller;

import com.bloodlink.project.Model.Donor;
import com.bloodlink.project.Service.DonorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/donors")
public class DonorController {

    private final DonorService donorService;

    public DonorController(DonorService donorService) {
        this.donorService = donorService;
    }

    @PostMapping
    public ResponseEntity<Donor> registerDonor(@RequestBody Donor donor) {
        return ResponseEntity.ok(donorService.registerDonor(donor));
    }

    @GetMapping
    public ResponseEntity<List<Donor>> getAllDonors() {
        return ResponseEntity.ok(donorService.getAllDonors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Donor> getDonorById(@PathVariable Long id) {
        return ResponseEntity.ok(donorService.getDonorById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Donor> updateDonor(
            @PathVariable Long id,
            @RequestBody Donor donor) {

        return ResponseEntity.ok(
                donorService.updateDonor(id, donor)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDonor(@PathVariable Long id) {

        donorService.deleteDonor(id);

        return ResponseEntity.ok("Donor deleted successfully");
    }

    @GetMapping("/search")
    public ResponseEntity<List<Donor>> searchDonors(
            @RequestParam String bloodGroup,
            @RequestParam String city) {

        return ResponseEntity.ok(
                donorService.searchDonors(bloodGroup, city)
        );
    }

    @PutMapping("/{id}/donate")
    public ResponseEntity<Donor> recordDonation(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                donorService.recordDonation(id, LocalDate.now())
        );
    }
}