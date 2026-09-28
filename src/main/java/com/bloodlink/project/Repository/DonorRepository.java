package com.bloodlink.project.Repository;

import com.bloodlink.project.Model.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonorRepository extends JpaRepository<Donor, Long> {

    List<Donor> findByBloodGroupAndCity(String bloodGroup, String city);

    List<Donor> findByBloodGroup(String bloodGroup);

    List<Donor> findByCity(String city);
}