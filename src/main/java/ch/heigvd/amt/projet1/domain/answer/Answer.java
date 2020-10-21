package ch.heigvd.amt.projet1.domain.answer;

import ch.heigvd.amt.projet1.domain.IEntity;
import ch.heigvd.amt.projet1.domain.Id;
import ch.heigvd.amt.projet1.domain.question.Question;
import ch.heigvd.amt.projet1.domain.question.QuestionId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Answer implements IEntity<Answer, AnswerId> {
    AnswerId id;
    protected String author;
    protected String content;
    String questionId;
    protected int vote;

    @Override
    public AnswerId getId() {
        return id;
    }

    @Override
    public Answer deepClone() {
        return this.toBuilder().id(new AnswerId(id.asString())).build();
    }

    public static class AnswerBuilder {
        public Answer build() {
            if (id == null) {
                id = new AnswerId();
            }
            if (date == null){
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date = formatter.format(new Date());
            }

            return new Answer(id, author, content, questionId, date ,vote);
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
