package ma.youcode.myrh.services.implementations;

import ma.youcode.myrh.dtos.JobSeekerDTO;
import ma.youcode.myrh.dtos.ProfileDTO;
import ma.youcode.myrh.dtos.QuestionDTO;
import ma.youcode.myrh.models.*;
import ma.youcode.myrh.repositories.IProfileRepository;
import ma.youcode.myrh.services.IProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfileService implements IProfileService {
    @Autowired
    IProfileRepository profileRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public ProfileDTO save(ProfileDTO profileDTO) {
        Optional<Profile> profileOptional = profileRepository.findByTitle(profileDTO.getTitle());

        if (profileOptional.isPresent()) {
            return null;
        }
        Profile profile = modelMapper.map(profileDTO, Profile.class);
        profile = profileRepository.saveAndFlush(profile);

        return modelMapper.map(profile, ProfileDTO.class);
    }

    @Override
    public List<ProfileDTO> findAll() {
        List<Profile> profiles = profileRepository.findAll();
        return profiles.stream()
                .map(this::mapToProfileDTO)
                .collect(Collectors.toList());
    }

    private ProfileDTO mapToProfileDTO(Profile profile) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(profile.getId());
        profileDTO.setTitle(profile.getTitle());

        List<JobSeekerDTO> jobSeekerDTOs = profile.getJobSeekers().stream()
                .map(jobSeeker -> modelMapper.map(jobSeeker, JobSeekerDTO.class))
                .collect(Collectors.toList());

        profileDTO.setJobSeekers(jobSeekerDTOs);

        List<QuestionDTO> questionDTOs = profile.getQuestions().stream()
                .map(question -> modelMapper.map(question, QuestionDTO.class))
                .collect(Collectors.toList());

        profileDTO.setQuestions(questionDTOs);

        return profileDTO;
    }

    @Override
    public ProfileDTO findByTitle(String title) {
        Optional<Profile> profileOptional = profileRepository.findByTitle(title);
        if (profileOptional.isPresent()) {
            Profile profile = profileOptional.get();
            System.out.println(profileOptional.get());
            System.out.println("profile");
            return modelMapper.map(profile, ProfileDTO.class);
        }
        return null;
    }

    @Override
    public ProfileDTO findByID(Long id) {
        Optional<Profile> profileOptional = profileRepository.findById(id);
        return profileOptional.map(this::mapToProfileDTO).orElse(null);
    }

    @Override
    public ProfileDTO addQuestion(ProfileDTO profileDTO, QuestionDTO questionDTO) {
        return null;
    }
}