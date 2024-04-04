package quizBasic.Application.Service;

import quizBasic.Application.Domain.Topic;

import java.util.Set;

public interface TopicService {

    Set<Topic> unSolvedQuiz(String email);

    Topic getTopicById(Long topicId);

    String getTopicNameById(Long topicId);

}
