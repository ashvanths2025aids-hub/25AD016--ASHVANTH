package com.bloodlink.project.Repository;

import com.bloodlink.project.Model.BloodGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BloodGroupRepository extends JpaRepository<BloodGroup, Long> {

    Optional<BloodGroup> findByBloodGroup(String bloodGroup);
}