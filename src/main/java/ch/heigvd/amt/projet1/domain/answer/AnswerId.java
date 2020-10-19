package ch.heigvd.amt.projet1.domain.answer;

import ch.heigvd.amt.projet1.domain.Id;

import java.util.UUID;

public class AnswerId extends Id {
    public  AnswerId(){
        super();
    }
    public  AnswerId(String id){
        super(id);
    }
    public AnswerId(UUID id){
        super(id);
    }
}
