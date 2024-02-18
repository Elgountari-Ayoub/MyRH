package ma.youcode.myrh.dtos;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import ma.youcode.myrh.models.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProfileDTO extends UserDTO{
    private Long id;
    private String title;
    private List<JobSeeker> jobSeekers = new ArrayList<>();
    private List<Question> questions = new ArrayList<>();
}
