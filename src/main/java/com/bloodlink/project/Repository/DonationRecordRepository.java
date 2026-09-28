package com.bloodlink.project.Repository;

import com.bloodlink.project.Model.DonationRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRecordRepository extends JpaRepository<DonationRecord, Long> {

    List<DonationRecord> findByDonorId(Long donorId);
}