package ma.youcode.myrh.services.implementations;

import ma.youcode.myrh.dtos.ProfileDTO;
import ma.youcode.myrh.dtos.QuestionDTO;
import ma.youcode.myrh.dtos.ResumeDTO;
import ma.youcode.myrh.models.*;
import ma.youcode.myrh.repositories.IJobOfferRepository;
import ma.youcode.myrh.repositories.IProfileRepository;
import ma.youcode.myrh.repositories.IResumeRepository;
import ma.youcode.myrh.repositories.UserRepository;
import ma.youcode.myrh.services.FilesStorageService;
import ma.youcode.myrh.services.IProfileService;
import ma.youcode.myrh.services.IResumeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
                .map(profile -> modelMapper.map(profile, ProfileDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ProfileDTO findByTitle(String title) {
        Optional<Profile> profileOptional = profileRepository.findByTitle(title);
        if (profileOptional.isPresent()){
            Profile profile = profileOptional.get();
            System.out.println(profileOptional.get());
            System.out.println("profile");
            return modelMapper.map(profile, ProfileDTO.class);
        }
        return null;
    }

    @Override
    public ProfileDTO addQuestion(ProfileDTO profileDTO, QuestionDTO questionDTO) {
        return null;
    }
}