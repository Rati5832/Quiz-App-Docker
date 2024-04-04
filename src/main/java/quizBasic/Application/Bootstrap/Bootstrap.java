package quizBasic.Application.Bootstrap;

import quizBasic.Application.Domain.Option;
import quizBasic.Application.Domain.Questions;
import quizBasic.Application.Domain.Topic;
import quizBasic.Application.Repository.QuestionRepository;
import quizBasic.Application.Repository.TopicRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    public TopicRepository topicRepository;
    public QuestionRepository questionRepository;

    public Bootstrap(TopicRepository topicRepository, QuestionRepository questionRepository) {
        this.topicRepository = topicRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        List<Topic> topicList = listofTopics();
        topicRepository.saveAll(topicList);

    }

    public List<Topic> listofTopics() {
        List<Topic> topicList = new ArrayList<>();

        //Games
        Topic gamesTopic = new Topic();
        gamesTopic.setName("Games");
        topicList.add(gamesTopic);

        Set<String> optionalAnswers = new HashSet<>();
        optionalAnswers.add("Minecraft");
        optionalAnswers.add("Tetris");



        Set<String> optionalAnswers2 = new HashSet<>();
        optionalAnswers2.add("League Of Legends");
        optionalAnswers2.add("GTA V");


        Set<String> optionalAnswers3 = new HashSet<>();
        optionalAnswers3.add("October 1958");
        optionalAnswers3.add("November 1963");

        saveQuestion("Best-Selling Video Games", optionalAnswers, gamesTopic, "Tetris");
        saveQuestion("Most Watched Video Games", optionalAnswers2, gamesTopic, "GTA V");
        saveQuestion("First Video Game Create In? ", optionalAnswers3, gamesTopic, "October 1958");

        //Movies

        Topic topic3 = new Topic();
        topic3.setName("Movies");
        topicList.add(topic3);

        Set<String> optionalAnswers4 = new HashSet<>();
        optionalAnswers4.add("A Coca-Cola can");
        optionalAnswers4.add("A Starbucks cup");


        Set<String> optionalAnswers5 = new HashSet<>();
        optionalAnswers5.add("Mark Hamill");
        optionalAnswers5.add("Sean Penn");

        Set<String> optionalAnswers6 = new HashSet<>();
        optionalAnswers6.add("Reservoir Dogs");
        optionalAnswers6.add("Se7en");

        saveQuestion("What item is in every Fight Club scene?", optionalAnswers4, topic3, "A Starbucks cup");
        saveQuestion("Which actor hasn’t played the Joker?", optionalAnswers5, topic3, "Sean Penn");
        saveQuestion("Which movie is this quote from: 'What’s in the box?'", optionalAnswers6, topic3, "Se7en");

        //Books
        Topic topic4 = new Topic();
        topic4.setName("Books");
        topicList.add(topic4);

        Set<String> optionalAnswers7 = new HashSet<>();
        optionalAnswers7.add("Rodion Raskolnikov");
        optionalAnswers7.add("Dmitri Razumikhin");


        Set<String> optionalAnswers8 = new HashSet<>();
        optionalAnswers8.add("Albert Camus");
        optionalAnswers8.add("Franz Kafka");


        Set<String> optionalAnswers9 = new HashSet<>();
        optionalAnswers9.add("The Stranger");
        optionalAnswers9.add("The Wisdom Of Life");

        saveQuestion("What is the name of the main character in 'crime and punishment'", optionalAnswers7, topic4, "Rodion Raskolnikov");
        saveQuestion("Who is the author of 'The Metamorphosis'", optionalAnswers8, topic4, "Franz Kafka");
        saveQuestion("Which book is written by Albert Camus", optionalAnswers9, topic4, "The Stranger");

        return topicList;

    }

    public void saveQuestion(String questionText, Set<String> optionalAnswers, Topic topic, String correctAnswer) {
        Questions question = new Questions();
        question.setQuestion(questionText);

        for (String answer : optionalAnswers) {
            Option option = new Option();
            option.setOptiontext(answer);
            option.setQuestions(question);
            // Check if the current answer matches the correct answer
            if (answer.equals(correctAnswer)) {
                option.setCorrect(true); // Mark the option as correct
            }
            question.getOptions().add(option);
        }

        topic.getQuestions().add(question);
        questionRepository.save(question);
    }


}
