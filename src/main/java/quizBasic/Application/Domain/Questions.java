package quizBasic.Application.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    @OneToMany(mappedBy = "questions", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Option> options = new HashSet<>();

    @ManyToMany(mappedBy = "questions")
    private Set<Topic> topics = new HashSet<>();

    public void addOption(Option option) {
        this.options.add(option);
    }

}
