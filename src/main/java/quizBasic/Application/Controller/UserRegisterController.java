package quizBasic.Application.Controller;

import quizBasic.Application.Domain.UserForm;
import quizBasic.Application.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserRegisterController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String showRegistrationForm(){

        return "register";

    }

    @PostMapping("/register")
    public String userRegistration(@RequestParam String firstName,
                                   @RequestParam String lastName,
                                   @RequestParam String email,
                                   @RequestParam String password){

        UserForm user = new UserForm();
        user.setFirstname(firstName);
        user.setLastname(lastName);
        user.setEmail(email);
        user.setPassword(password);

        userRepository.save(user);

        return "redirect:/index";

    }


}
