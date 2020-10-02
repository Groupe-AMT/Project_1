package ch.heigvd.amt.projet1.domain.person;

import ch.heigvd.amt.projet1.domain.Id;

import java.util.UUID;

public class PersonId extends Id {
    public  PersonId(){
        super();
    }
    public  PersonId(String id){
        super(id);
    }
    public PersonId(UUID id){
        super(id);
    }
}
