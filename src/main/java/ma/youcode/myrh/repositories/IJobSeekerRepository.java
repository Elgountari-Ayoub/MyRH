package ma.youcode.myrh.repositories;

import ma.youcode.myrh.models.JobSeeker;
import ma.youcode.myrh.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IJobSeekerRepository extends JpaRepository<JobSeeker, Long> {
    Optional<JobSeeker> findByEmail(String email);
}
