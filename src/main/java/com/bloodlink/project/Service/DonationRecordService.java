package com.bloodlink.project.Service;

import com.bloodlink.project.Exception.DonorCooldownException;
import com.bloodlink.project.Exception.ResourceNotFoundException;
import com.bloodlink.project.Model.DonationRecord;
import com.bloodlink.project.Model.Donor;
import com.bloodlink.project.Repository.DonationRecordRepository;
import com.bloodlink.project.Repository.DonorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DonationRecordService {

    private final DonationRecordRepository donationRecordRepository;
    private final DonorRepository donorRepository;

    public DonationRecordService(
            DonationRecordRepository donationRecordRepository,
            DonorRepository donorRepository) {

        this.donationRecordRepository = donationRecordRepository;
        this.donorRepository = donorRepository;
    }

    public DonationRecord recordDonation(DonationRecord record) {

        Donor donor = donorRepository.findById(record.getDonorId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Donor not found with id: "
                                        + record.getDonorId()
                        ));

        // Check 90-day donation cooldown
        if (donor.getLastDonationDate() != null) {

            LocalDate nextAvailableDate =
                    donor.getLastDonationDate().plusDays(90);

            if (LocalDate.now().isBefore(nextAvailableDate)) {

                throw new DonorCooldownException(
                        "Donor is not available for donation until "
                                + nextAvailableDate
                );
            }
        }

        // Record today's donation
        record.setDonationDate(LocalDate.now());

        // Update donor status
        donor.setLastDonationDate(record.getDonationDate());
        donor.setAvailable(false);

        donorRepository.save(donor);

        return donationRecordRepository.save(record);
    }

    public List<DonationRecord> getAllDonations() {
        return donationRecordRepository.findAll();
    }

    public List<DonationRecord> getDonationsByDonor(Long donorId) {
        return donationRecordRepository.findByDonorId(donorId);
    }

    public DonationRecord getDonationById(Long id) {

        return donationRecordRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Donation record not found with id: "
                                        + id
                        ));
    }

    public void deleteDonation(Long id) {

        DonationRecord record = getDonationById(id);

        donationRecordRepository.delete(record);
    }
}