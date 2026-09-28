package com.bloodlink.project.Controller;

import com.bloodlink.project.Model.BloodGroup;
import com.bloodlink.project.Service.BloodGroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blood-groups")
public class BloodGroupController {

    private final BloodGroupService bloodGroupService;

    public BloodGroupController(BloodGroupService bloodGroupService) {
        this.bloodGroupService = bloodGroupService;
    }

    @PostMapping
    public ResponseEntity<BloodGroup> addBloodGroup(
            @RequestBody BloodGroup bloodGroup) {

        return ResponseEntity.ok(
                bloodGroupService.addBloodGroup(bloodGroup)
        );
    }

    @GetMapping
    public ResponseEntity<List<BloodGroup>> getAllBloodGroups() {

        return ResponseEntity.ok(
                bloodGroupService.getAllBloodGroups()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<BloodGroup> getBloodGroupById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                bloodGroupService.getBloodGroupById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<BloodGroup> updateBloodGroup(
            @PathVariable Long id,
            @RequestBody BloodGroup bloodGroup) {

        return ResponseEntity.ok(
                bloodGroupService.updateBloodGroup(id, bloodGroup)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBloodGroup(
            @PathVariable Long id) {

        bloodGroupService.deleteBloodGroup(id);

        return ResponseEntity.ok(
                "Blood group deleted successfully"
        );
    }
}