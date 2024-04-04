package quizBasic.Application.Domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "usertopicscore")
public class UserTopicScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int score;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserForm user;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;



}
