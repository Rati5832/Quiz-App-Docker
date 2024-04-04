package quizBasic.Application.Controller;

import quizBasic.Application.Domain.Topic;
import quizBasic.Application.Domain.UserForm;
import quizBasic.Application.Domain.UserTopicScore;
import quizBasic.Application.Repository.UserRepository;
import quizBasic.Application.Service.QuizService;
import quizBasic.Application.Service.TopicService;
import quizBasic.Application.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@Controller
@RequiredArgsConstructor
public class SubmitQuizController {


    private final QuizService quizService;
    private final TopicService topicService;
    private final UserService userService;
    private final UserRepository userRepository;


    @RequestMapping(value = "/submitQuiz/{quizId}/{userId}", method = {RequestMethod.GET, RequestMethod.POST})
    public String submitQuiz(@RequestParam Map<String, String> allParams,
                             @PathVariable Long quizId,
                             @PathVariable Long userId,
                             Model model) {

        String quizTopic = topicService.getTopicNameById(quizId);


        Map<String, String> correctAnswers = quizService.getCorrectAnswersForQuizTopic(quizTopic);
        int score = calculateScore(allParams, correctAnswers);

        UserForm userForm = userService.getUserById(userId);
        Topic topic = topicService.getTopicById(quizId);

        UserTopicScore userTopicScore = new UserTopicScore();
        userTopicScore.setUser(userForm);
        userTopicScore.setTopic(topic);
        userTopicScore.setScore(score);

        userForm.getTopicScores().add(userTopicScore);
        userForm.getSubscriptions().add(topic);
        userRepository.save(userForm);


        model.addAttribute("score", score);
        model.addAttribute("user",userForm);

        return "quizresult";
    }

    private int calculateScore(Map<String, String> submittedAnswers, Map<String, String> correctAnswers) {
        int score = 0;

        for (Map.Entry<String, String> entry : submittedAnswers.entrySet()) {
            String submittedKey = entry.getKey();
            String submittedAnswer = entry.getValue();

            String questionId = submittedKey.split("_")[1];


            String correctAnswer = correctAnswers.get(questionId);
            if (correctAnswer != null && correctAnswer.equals(submittedAnswer)) {
                score++;
            }
        }
        return score;
    }
}