package ma.youcode.myrh.dtos;

import lombok.Data;

import java.util.List;

@Data
public class JobSeekerProfilesDTO extends UserDTO{
    private List<ProfileDTO> profiles;
}
