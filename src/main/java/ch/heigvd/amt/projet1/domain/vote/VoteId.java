package ch.heigvd.amt.projet1.domain.vote;

import ch.heigvd.amt.projet1.domain.Id;

import java.util.UUID;

public class VoteId extends Id {
    public VoteId(){
        super();
    }
    public VoteId(String id){
        super(id);
    }
    public VoteId(UUID id){
        super(id);
    }
}
