package ch.heigvd.amt.projet1.domain.question;

import ch.heigvd.amt.projet1.domain.Id;

import java.util.UUID;

public class QuestionId extends Id {
    public  QuestionId(){
        super();
    }
    public  QuestionId(String id){
        super(id);
    }
    public QuestionId(UUID id){
        super(id);
    }
}
