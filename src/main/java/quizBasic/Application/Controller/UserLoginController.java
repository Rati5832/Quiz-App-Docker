package quizBasic.Application.Controller;

import quizBasic.Application.Domain.UserForm;
import quizBasic.Application.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Optional;


@Controller
public class UserLoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String showRegistrationForm() {

        return "login";

    }

    @PostMapping("/login")
    public String userRegistration(@RequestParam(value = "email", required = false) String email,
                                   @RequestParam String password,
                                   Model model, HttpSession session) {


        Optional<UserForm> userFormOptional = userRepository.findByEmail(email);
        if (userFormOptional.isPresent()) {

            UserForm userForm = userFormOptional.get();

            if (userForm.getPassword().equals(password)) {

                session.setAttribute("loggedInUser", email);

                return "redirect:/index";

            } else {

                model.addAttribute("errorMessage", "Invalid Username Or Password!");

                return "login";

            }

        } else {

            model.addAttribute("errorMessage", "Invalid Username Or Password!");

            return "login";

        }
    }
}
