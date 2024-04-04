package quizBasic.Application.Service;

import quizBasic.Application.Domain.Option;
import quizBasic.Application.Domain.Questions;
import quizBasic.Application.Domain.Topic;
import quizBasic.Application.Repository.QuestionRepository;
import quizBasic.Application.Repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final TopicRepository topicRepository;
    private final QuestionRepository questionRepository;


    public Set<Questions> startQuizQuestions(Long topicId) {

        Optional<Topic> topicOptional = topicRepository.findById(topicId);

        if (topicOptional.isPresent()) {

            Topic topic = topicOptional.get();

            return topic.getQuestions();

        } else {

            throw new EntityNotFoundException("Topic Not Found");

        }

    }

    public Map<String, String> getCorrectAnswersForQuizTopic(String quizTopic) {
        Map<String, String> correctAnswers = new HashMap<>();

        List<Questions> questions = questionRepository.findByTopicsName(quizTopic);

        for (Questions question : questions) {
            for (Option option : question.getOptions()) {
                if (option.isCorrect()) {
                    correctAnswers.put(String.valueOf(question.getId()), option.getOptiontext());
                    break;
                }
            }
        }

        return correctAnswers;
    }
}
