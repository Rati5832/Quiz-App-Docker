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
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "topic_questions",
            joinColumns = @JoinColumn(name = "topic_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private Set<Questions> questions = new HashSet<>();


    @ManyToMany(mappedBy = "subscriptions")
    private Set<UserForm> users = new HashSet<>();

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private Set<UserTopicScore> topicScores = new HashSet<>();

}
