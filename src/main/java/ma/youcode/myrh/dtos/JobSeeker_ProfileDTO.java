package ma.youcode.myrh.dtos;

import lombok.Data;
import ma.youcode.myrh.models.Profile;

import java.util.ArrayList;
import java.util.List;

@Data
public class JobSeeker_ProfileDTO {
    private JobSeekerDTO jobSeeker;
    private ProfileDTO profile;
}
