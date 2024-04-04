package quizBasic.Application.Service;

import quizBasic.Application.Domain.Topic;
import quizBasic.Application.Domain.UserForm;
import quizBasic.Application.Repository.TopicRepository;
import quizBasic.Application.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;


    @Override
    public Set<Topic> unSolvedQuiz(String email) {

        UserForm user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User Not Found With Given Email!"));

        Set<Topic> passedQuiz = user.getSubscriptions();

        return topicRepository.findAll().stream().filter(topic -> !passedQuiz.contains(topic)).collect(Collectors.toSet());

    }


    @Override
    public Topic getTopicById(Long topicId) {

        Optional<Topic> topicOptional = topicRepository.findById(topicId);

        if(topicOptional.isPresent()){

            Topic topic = topicOptional.get();

            return topic;

        } else {

            throw new EntityNotFoundException("Topic Not Found With Given ID" + topicId);

        }


    }

    @Override
    public String getTopicNameById(Long topicId) {

        Optional<Topic> topicOptional = topicRepository.findById(topicId);
        if (topicOptional.isPresent()) {
            Topic topic = topicOptional.get();
            return topic.getName();
        } else {
            throw new EntityNotFoundException("Topic Not Found With Given ID" + topicId);
        }
    }
}
