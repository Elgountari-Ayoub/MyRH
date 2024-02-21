package ma.youcode.myrh.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.ToString;
import ma.youcode.myrh.models.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProfileDTO{
    private Long id;
    private String title;
    private List<JobSeekerDTO> jobSeekers;
    private List<QuestionDTO> questions ;
}
