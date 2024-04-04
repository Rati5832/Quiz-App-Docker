package quizBasic.Application.Service;

import quizBasic.Application.Domain.UserForm;

import java.util.Map;

public interface UserService {

    UserForm getUserById(Long id);

    Map<String,Integer> getScoresForUser(Long userId);


}
