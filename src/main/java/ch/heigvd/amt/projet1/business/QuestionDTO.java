package ch.heigvd.amt.projet1.business;

import ch.heigvd.amt.projet1.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionDTO {
    List<Question> content;

    public QuestionDTO(){
        this.content = new ArrayList<Question>();

    }
}
