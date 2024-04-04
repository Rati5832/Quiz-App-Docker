package quizBasic.Application.Domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "userform")
public class UserForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    @ManyToMany
    @JoinTable(name = "user_subscriptions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "subscription_id"))
    private Set<Topic> subscriptions = new HashSet<>();


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserTopicScore> topicScores = new HashSet<>();

    public int getScoreForTopic(Long topicId){

        for(UserTopicScore score : topicScores){

            if(score.getTopic().getId().equals(topicId)){

                return score.getScore();

            }

        }
        return 0;
    }

}
