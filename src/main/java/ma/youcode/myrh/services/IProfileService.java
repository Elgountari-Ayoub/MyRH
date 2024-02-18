package ma.youcode.myrh.services;

import ma.youcode.myrh.dtos.ProfileDTO;
import ma.youcode.myrh.dtos.QuestionDTO;

import java.util.List;

public interface IProfileService {
     ProfileDTO save(ProfileDTO jobOfferDTO);
     List<ProfileDTO> findAll();
     ProfileDTO findByTitle(String title);
     ProfileDTO addQuestion(ProfileDTO profileDTO, QuestionDTO questionDTO);
}
