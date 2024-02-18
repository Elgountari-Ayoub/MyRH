package ma.youcode.myrh.repositories;

import ma.youcode.myrh.models.Agent;
import ma.youcode.myrh.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuestionRepository extends JpaRepository<Question, Long> {
}
