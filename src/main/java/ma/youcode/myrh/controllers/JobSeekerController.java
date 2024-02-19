package ma.youcode.myrh.controllers;


import ma.youcode.myrh.dtos.JobSeekerDTO;
import ma.youcode.myrh.dtos.JobSeeker_ProfileDTO;
import ma.youcode.myrh.dtos.ProfileDTO;
import ma.youcode.myrh.models.JobSeeker;
import ma.youcode.myrh.services.IJobOfferService;
import ma.youcode.myrh.services.IJobSeekerService;
import ma.youcode.myrh.services.implementations.JobSeekerService;
import ma.youcode.myrh.services.implementations.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/jobSeekers")
@CrossOrigin("*")
@RestController
public class JobSeekerController {
    @Autowired
    JobSeekerService jobSeekerService;

    @PostMapping("/addProfile")
    public ResponseEntity<JobSeekerDTO> addProfile(@RequestBody JobSeeker_ProfileDTO jobSeeker_profileDTO) {
        return ResponseEntity.ok(jobSeekerService.addProfile(jobSeeker_profileDTO));
    }
//    @GetMapping()
//    public ResponseEntity<List<JobSeekerDTO>> getAll(Pageable pageable) {
//        Page<RecruiterDTO> recruiters = recruiterService.findAll(pageable);
//        return ResponseEntity.ok(recruiters);
//    }



}
