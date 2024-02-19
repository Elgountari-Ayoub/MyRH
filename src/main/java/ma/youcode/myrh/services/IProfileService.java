package ma.youcode.myrh.services;

import ma.youcode.myrh.dtos.ProfileDTO;
import ma.youcode.myrh.dtos.QuestionDTO;
import ma.youcode.myrh.models.Profile;

import java.util.List;

public interface IProfileService {
     ProfileDTO save(ProfileDTO jobOfferDTO);
     List<Profile> findAll();
     ProfileDTO findByTitle(String title);
     ProfileDTO addQuestion(ProfileDTO profileDTO, QuestionDTO questionDTO);
}
