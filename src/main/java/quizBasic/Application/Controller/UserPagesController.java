package quizBasic.Application.Controller;

import quizBasic.Application.Domain.UserForm;
import quizBasic.Application.Repository.UserRepository;
import quizBasic.Application.Service.QuizService;
import quizBasic.Application.Service.TopicService;
import quizBasic.Application.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.HttpSession;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class UserPagesController {

    private final TopicService topicService;
    private final QuizService quizService;
    private final UserService userService;
    private final UserRepository userRepository;


    @GetMapping({"/", "/index"})
    public String showWelcomePage(Model model, HttpSession session) {

        String email = (String) session.getAttribute("loggedInUser");
        Optional<UserForm> userOPT = userRepository.findByEmail(email);

        if (email != null) {

            UserForm user = userOPT.get();

            model.addAttribute("user",user);
            model.addAttribute("notSub",topicService.unSolvedQuiz(email));

        }

        return "welcomepage";

    }

    @RequestMapping(value = "/{id}/{topicId}/quiz", method = {RequestMethod.GET, RequestMethod.POST})
    public String QuizStart(@PathVariable Long id, @PathVariable Long topicId, Model model){

        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("quizQuestions", quizService.startQuizQuestions(topicId));
        model.addAttribute("topic", topicService.getTopicById(topicId));

        return "startingquiz";

    }


    @RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
    public String logout(HttpSession session) {

        session.invalidate();
        return "redirect:/";
    }


}

