package ma.youcode.myrh.repositories;

import ma.youcode.myrh.models.Profile;
import ma.youcode.myrh.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByTitle(String title);
}
