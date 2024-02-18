package ma.youcode.myrh.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

//@EqualsAndHashCode(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@Entity
public class Recruiter extends User{

    private String login;
    private String phone;
    private String address;
    private String image;

    private String codeValidation;
    private LocalDateTime codeValidationTimestamp = LocalDateTime.now();
    private Boolean isValid = false;

    @OneToMany(mappedBy = "recruiter", cascade = CascadeType.ALL)
    private List<JobOffer> jobOffers;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;
}