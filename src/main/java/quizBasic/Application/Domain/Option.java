package quizBasic.Application.Domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String optiontext;
    private boolean correct;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Questions questions;



}
