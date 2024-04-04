package quizBasic.Application.Repository;

import quizBasic.Application.Domain.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Questions,Long> {

    List<Questions> findByTopicsName(String topicName);

}
