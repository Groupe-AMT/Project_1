package ch.heigvd.amt.projet1.domain.question;

import ch.heigvd.amt.projet1.domain.IEntity;
import lombok.*;

import java.util.List;
@Getter
@Setter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Question  implements IEntity<Question,QuestionId> {
    // Variables
    String Subject;
    QuestionId id; //contient un UUID qui identifie la question de façon unique (à utiliser comme id dans la DB)
    protected String author;
    protected String content;
    List<String> Tags ;
    protected int vote;

    @Override
    public Question deepClone() {
        return this.toBuilder().id(new QuestionId(id.asString())).build();
    }
    public static class QuestionBuilder {
        public Question build() {
            if (id == null) {
                id = new QuestionId();
            }

            return new Question(Subject, id, author, content, Tags, vote);
        }
    }

    public int upVote(){
        this.vote = this.vote + 1;
        return this.vote;
    }

    public int downVote(){
        this.vote = this.vote - 1;
        return this.vote;
    }
}