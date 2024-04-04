package quizBasic.Application.Service;

import quizBasic.Application.Domain.Topic;
import quizBasic.Application.Domain.UserForm;
import quizBasic.Application.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserForm getUserById(Long id) {

        Optional<UserForm> userFormOptional = userRepository.findById(id);

        if(userFormOptional.isPresent()){

            UserForm user = userFormOptional.get();

            return user;
        }

        else{

            throw new EntityNotFoundException("Entity Not Found!");

        }

    }

    @Override
    public Map<String, Integer> getScoresForUser(Long userId) {

        UserForm user = getUserById(userId);

        Map<String, Integer> scores = new HashMap<>();

        for(Topic topic : user.getSubscriptions()){

            int score = user.getScoreForTopic(topic.getId());
            scores.put(topic.getName(),score);
        }

        return scores;

    }
}
