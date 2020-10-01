package ch.heigvd.amt.projet1.business;

import ch.heigvd.amt.projet1.model.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionDTO {
    private List<Question> content;
    public List<Question> getContent(){
        return this.content;
    }
    public void setContent(List<Question> content1){
        this.content = content1;
    }
    public void addContent(Question question){
        this.content.add(question);
    }
    public QuestionDTO(){
        this.content = new ArrayList<Question>();
        this.content.add(new Question("test", "test", "test", Collections.singletonList("testing")));
    }
}
