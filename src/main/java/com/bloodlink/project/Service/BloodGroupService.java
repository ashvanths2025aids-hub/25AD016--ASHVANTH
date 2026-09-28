package com.bloodlink.project.Service;

import com.bloodlink.project.Exception.ResourceNotFoundException;
import com.bloodlink.project.Model.BloodGroup;
import com.bloodlink.project.Repository.BloodGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodGroupService {

    private final BloodGroupRepository bloodGroupRepository;

    public BloodGroupService(BloodGroupRepository bloodGroupRepository) {
        this.bloodGroupRepository = bloodGroupRepository;
    }

    public BloodGroup addBloodGroup(BloodGroup bloodGroup) {
        return bloodGroupRepository.save(bloodGroup);
    }

    public List<BloodGroup> getAllBloodGroups() {
        return bloodGroupRepository.findAll();
    }

    public BloodGroup getBloodGroupById(Long id) {
        return bloodGroupRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Blood group not found with id: " + id
                        ));
    }

    public BloodGroup updateBloodGroup(
            Long id,
            BloodGroup details) {

        BloodGroup bloodGroup = getBloodGroupById(id);

        bloodGroup.setBloodGroup(details.getBloodGroup());
        bloodGroup.setDescription(details.getDescription());

        return bloodGroupRepository.save(bloodGroup);
    }

    public void deleteBloodGroup(Long id) {

        BloodGroup bloodGroup = getBloodGroupById(id);

        bloodGroupRepository.delete(bloodGroup);
    }
}