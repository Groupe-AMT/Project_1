package ch.heigvd.amt.projet1.domain.comment;

import ch.heigvd.amt.projet1.domain.IEntity;
import ch.heigvd.amt.projet1.domain.Id;
import ch.heigvd.amt.projet1.domain.answer.Answer;
import ch.heigvd.amt.projet1.domain.answer.AnswerId;
import ch.heigvd.amt.projet1.domain.question.QuestionId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Comment implements IEntity<Comment, CommentId> {
    CommentId id;
    protected String author;
    protected String content;
    protected String type;
    QuestionId questionId;
    AnswerId answerId;

    @Override
    public CommentId getId() {
        return id;
    }

    @Override
    public Comment deepClone() {
        return this.toBuilder().id(new CommentId(id.asString())).build();
    }

    public static class CommentBuilder {
        public Comment build() {
            if (id == null) {
                id = new CommentId();
            }
            return new Comment(id, author, content, type, questionId, answerId);
        }
    }
}
