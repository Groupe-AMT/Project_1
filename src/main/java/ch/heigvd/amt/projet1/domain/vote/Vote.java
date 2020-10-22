package ch.heigvd.amt.projet1.domain.vote;

import ch.heigvd.amt.projet1.domain.IEntity;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Vote implements IEntity<Vote, VoteId> {
    // Variables
    VoteId id; //contient un UUID qui identifie la question de façon unique (à utiliser comme id dans la DB)
    protected String author;
    protected String type;
    String questionId;
    String answerId;
    protected boolean note;


    @Override
    public Vote deepClone() {
        return this.toBuilder().id(new VoteId(id.asString())).build();
    }
    public static class VoteBuilder {
        public Vote build() {
            if (id == null) {
                id = new VoteId();
            }
            if(type.equals("answer"))
                questionId="null";
            else if (type.equals("question"))
                answerId="null";
            else{
                questionId ="null";
                answerId="null";
            }
            return new Vote( id, author, type, questionId,answerId, note);
        }
    }

}