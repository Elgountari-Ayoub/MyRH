package ma.youcode.myrh.services.implementations;

import ma.youcode.myrh.dtos.ProfileDTO;
import ma.youcode.myrh.dtos.QuestionDTO;
import ma.youcode.myrh.models.Profile;
import ma.youcode.myrh.models.Question;
import ma.youcode.myrh.repositories.IQuestionRepository;
import ma.youcode.myrh.services.IProfileService;
import ma.youcode.myrh.services.IQuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService implements IQuestionService {
    @Autowired
    IQuestionRepository questionRepository;
    @Autowired
    IProfileService profileService;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public QuestionDTO save(QuestionDTO questionDTO) {


        ProfileDTO profileOptional = profileService.findByTitle(questionDTO.getProfileDTO().getTitle());
        if (profileOptional != null) {
            Profile profile = modelMapper.map(questionDTO.getProfileDTO(), Profile.class);
            Question question = modelMapper.map(questionDTO, Question.class);
            questionRepository.save(question);
            return questionDTO;
        } else {
            return null;
        }

    }

    @Override
    public List<QuestionDTO> findAllByProfile(ProfileDTO profileDTO) {
        return null;
    }

}