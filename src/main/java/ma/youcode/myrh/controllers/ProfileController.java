package ma.youcode.myrh.controllers;


import ma.youcode.myrh.dao.response.JwtAuthenticationResponse;
import ma.youcode.myrh.dtos.ProfileDTO;
import ma.youcode.myrh.dtos.RecruiterDTO;
import ma.youcode.myrh.dtos.StatisticsDTO;
import ma.youcode.myrh.models.Profile;
import ma.youcode.myrh.services.implementations.ProfileService;
import ma.youcode.myrh.services.implementations.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/profiles")
@CrossOrigin("*")
@RestController
public class ProfileController {
    @Autowired
    ProfileService profileService;

    @PostMapping
    public ResponseEntity<ProfileDTO> create(@RequestBody ProfileDTO profileDTO) {
        return ResponseEntity.ok(profileService.save(profileDTO));
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileDTO> getById(@PathVariable Long profileId) {
        ProfileDTO profile = profileService.findByID(profileId);
        return ResponseEntity.ok(profile);
    }

    @GetMapping()
    public ResponseEntity<List<ProfileDTO>> getAll() {
        List<ProfileDTO> profiles = profileService.findAll();
        System.out.println(profiles);
        return ResponseEntity.ok(profiles);
    }


}
