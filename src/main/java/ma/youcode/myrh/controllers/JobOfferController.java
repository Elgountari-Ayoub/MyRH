package ma.youcode.myrh.controllers;


import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import ma.youcode.myrh.dtos.JobOfferDTO;
import ma.youcode.myrh.dtos.JobSeekerProfilesDTO;
import ma.youcode.myrh.dtos.ResumeDTO;
import ma.youcode.myrh.models.JobSeeker;
import ma.youcode.myrh.models.Status;
import ma.youcode.myrh.repositories.IJobSeekerRepository;
import ma.youcode.myrh.services.IJobOfferService;
import ma.youcode.myrh.services.IJobSeekerService;
import ma.youcode.myrh.services.JwtService;
import ma.youcode.myrh.services.UserService;
import ma.youcode.myrh.services.implementations.JobOfferService;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Enumeration;
import java.util.List;

@RequestMapping("api/v1/jobOffers")
@RestController
@CrossOrigin("*")
public class JobOfferController {

    @Autowired
    IJobOfferService jobOfferService;


    @GetMapping()
    public ResponseEntity<List<JobOfferDTO>> getAll() {
        List<JobOfferDTO> jobOffers = jobOfferService.findAll();
        return ResponseEntity.ok(jobOffers);
    }

    @GetMapping("/byUser/{id}")
    public ResponseEntity<List<JobOfferDTO>> getAllByUser(
            @PathVariable long id
    ) {
        List<JobOfferDTO> jobOffers = jobOfferService.findAllByUserId(id);
        return ResponseEntity.ok(jobOffers);
    }

    @PostMapping("/{recruiterId}")
    public ResponseEntity<JobOfferDTO> create(
            @PathVariable long recruiterId,
            @RequestBody JobOfferDTO jobOfferToSave) {
        JobOfferDTO jobOfferDTO = jobOfferService.save(jobOfferToSave, recruiterId);
        return ResponseEntity.ok(jobOfferDTO);
    }

    @GetMapping("/{recruiterId}")
    public ResponseEntity<List<JobOfferDTO>> getByRecruiter(
            @PathVariable long recruiterId) {
        List<JobOfferDTO> jobOfferDTOList = jobOfferService.findByRecruiterId(recruiterId);
        return ResponseEntity.ok(jobOfferDTOList);
    }

    @GetMapping("/search={title}")
    public ResponseEntity<List<JobOfferDTO>> searchByTitle(
            @PathVariable String title) {
        List<JobOfferDTO> jobOfferDTOList = jobOfferService.findByTitle(title);
        return ResponseEntity.ok(jobOfferDTOList);
    }

    @PostMapping("/{jobOfferId}/{newStatus}")
    public ResponseEntity<String> searchByTitle(
            @PathVariable long jobOfferId,
            @PathVariable String newStatus
    ) {
        String response = jobOfferService.updateStatus(jobOfferId, Status.valueOf(newStatus));
        return ResponseEntity.ok(response);
    }
    @GetMapping("/status={status}")
    public ResponseEntity<List<JobOfferDTO>> searchByStatus(
            @PathVariable String status, HttpServletRequest request) {

        List<JobOfferDTO> jobOfferDTOList = jobOfferService.findAllByStatus(status, request);
        return ResponseEntity.ok(jobOfferDTOList);
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<JobOfferDTO>> getAllPageable(Pageable pageable) {
        Page<JobOfferDTO> jobOffers = jobOfferService.findAllPageable(pageable);
        return ResponseEntity.ok(jobOffers);
    }

    @GetMapping("/{id}/{newStatus}")
    public ResponseEntity<String> updateStatus(
            @PathVariable long id,
            @PathVariable String newStatus
    ) {
        String resumeStatus = jobOfferService.updateStatus(id, Status.valueOf(newStatus));
        return ResponseEntity.ok(resumeStatus);
    }

}
