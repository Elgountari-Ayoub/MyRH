package ma.youcode.myrh.models;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@Entity
public class JobSeeker extends User{

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "jobseeker_profile",
            joinColumns = @JoinColumn(name = "jobseeker_id"),
            inverseJoinColumns = @JoinColumn(name = "profile_id")
    )
    private List<Profile> profiles;
}