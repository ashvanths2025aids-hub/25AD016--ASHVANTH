package com.bloodlink.project.Service;

import com.bloodlink.project.Exception.ResourceNotFoundException;
import com.bloodlink.project.Model.Donor;
import com.bloodlink.project.Repository.DonorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DonorService {

    private final DonorRepository donorRepository;

    public DonorService(DonorRepository donorRepository) {
        this.donorRepository = donorRepository;
    }

    public Donor registerDonor(Donor donor) {
        donor.setAvailable(true);
        return donorRepository.save(donor);
    }

    public List<Donor> getAllDonors() {
        updateAvailability();
        return donorRepository.findAll();
    }

    public Donor getDonorById(Long id) {
        return donorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Donor not found with id: " + id
                        ));
    }

    public Donor updateDonor(Long id, Donor donorDetails) {

        Donor donor = getDonorById(id);

        donor.setName(donorDetails.getName());
        donor.setPhone(donorDetails.getPhone());
        donor.setBloodGroup(donorDetails.getBloodGroup());
        donor.setCity(donorDetails.getCity());
        donor.setLastDonationDate(
                donorDetails.getLastDonationDate()
        );
        donor.setAvailable(donorDetails.isAvailable());

        return donorRepository.save(donor);
    }

    public void deleteDonor(Long id) {

        Donor donor = getDonorById(id);

        donorRepository.delete(donor);
    }

    public List<Donor> searchDonors(
            String bloodGroup,
            String city) {

        updateAvailability();

        return donorRepository
                .findByBloodGroupAndCity(bloodGroup, city)
                .stream()
                .filter(Donor::isAvailable)
                .toList();
    }

    public void updateAvailability() {

        List<Donor> donors = donorRepository.findAll();

        for (Donor donor : donors) {

            if (donor.getLastDonationDate() == null) {

                donor.setAvailable(true);

            } else {

                LocalDate availableDate =
                        donor.getLastDonationDate()
                                .plusDays(90);

                donor.setAvailable(
                        !LocalDate.now()
                                .isBefore(availableDate)
                );
            }
        }

        donorRepository.saveAll(donors);
    }

    public Donor recordDonation(
            Long donorId,
            LocalDate donationDate) {

        Donor donor = getDonorById(donorId);

        donor.setLastDonationDate(donationDate);

        donor.setAvailable(false);

        return donorRepository.save(donor);
    }
}