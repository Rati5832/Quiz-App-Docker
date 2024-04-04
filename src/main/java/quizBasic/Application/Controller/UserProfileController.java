package quizBasic.Application.Controller;

import quizBasic.Application.Domain.UserForm;
import quizBasic.Application.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserProfileController {

    private final UserService userService;


    @GetMapping("/myprofile/{userId}")
    public String getProfileView(@PathVariable Long userId, Model model){

        UserForm getUser = userService.getUserById(userId);


        model.addAttribute("user",getUser);
        model.addAttribute("userSubscriptions", getUser.getSubscriptions());


        Map<String,Integer> score = userService.getScoresForUser(userId);
        model.addAttribute("score",score);

        return "myprofile";

    }








}
