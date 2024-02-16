package ma.youcode.myrh.models;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "resumes")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;
}