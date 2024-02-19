package ma.youcode.myrh.services.implementations;

import jakarta.persistence.EntityNotFoundException;
import ma.youcode.myrh.dtos.*;
import ma.youcode.myrh.dtos.ProfileDTO;
import ma.youcode.myrh.models.*;
import ma.youcode.myrh.repositories.*;
import ma.youcode.myrh.repositories.IProfileRepository;
import ma.youcode.myrh.services.FilesStorageService;
import ma.youcode.myrh.services.IJobSeekerService;
import ma.youcode.myrh.services.IProfileService;
import ma.youcode.myrh.services.IProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobSeekerService implements IJobSeekerService {

    @Autowired
    IJobSeekerRepository jobSeekerRepository;

    @Autowired
    IProfileRepository profileRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public JobSeekerDTO addProfile(@RequestBody JobSeeker_ProfileDTO jobSeeker_profileDTO) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(jobSeeker_profileDTO.getJobSeeker().getId())
                .orElseThrow(() -> new EntityNotFoundException("job seeker not found"));

        Profile profile = profileRepository.findById(jobSeeker_profileDTO.getProfile().getId())
                .orElseThrow(() -> new EntityNotFoundException("profile not found"));

        jobSeeker.getProfiles().add(profile);
        jobSeeker = jobSeekerRepository.save(jobSeeker);
        return modelMapper.map(jobSeeker, JobSeekerDTO.class);
    }
}