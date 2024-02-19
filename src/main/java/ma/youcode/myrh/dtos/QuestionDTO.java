package ma.youcode.myrh.dtos;

import lombok.Data;
import ma.youcode.myrh.models.JobSeeker;
import ma.youcode.myrh.models.Profile;
import ma.youcode.myrh.models.Question;

import java.util.List;

@Data
public class QuestionDTO {
    private Long id;
    private String content;
    private Boolean answer;

//    private ProfileDTO profileDTO;
}
