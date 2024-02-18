package ma.youcode.myrh.services;

import ma.youcode.myrh.dtos.ProfileDTO;
import ma.youcode.myrh.dtos.QuestionDTO;
import ma.youcode.myrh.dtos.ResumeDTO;
import ma.youcode.myrh.models.ResumeStatus;

import java.util.List;

public interface IQuestionService {
     QuestionDTO save(QuestionDTO questionDTO);
     List<QuestionDTO> findAllByProfile(ProfileDTO profileDTO);
}
