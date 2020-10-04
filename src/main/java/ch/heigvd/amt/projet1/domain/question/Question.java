package ch.heigvd.amt.projet1.domain.question;


import ch.heigvd.amt.projet1.domain.IEntity;
import ch.heigvd.amt.projet1.domain.person.Person;
import ch.heigvd.amt.projet1.domain.person.PersonId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
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



    @Override
    public QuestionId getId() {
        return id;
    }

    @Override
    public Question deepClone() {
        return this.toBuilder().id(new QuestionId(id.asString())).build();
    }

    public QuestionId getQuestionId(){return this.id;} //pas de setter pour id car unique et crée à l'instanciation
    public static class QuestionBuilder {

        public Question build() {
            if (id == null) {
                id = new QuestionId();
            }
            return new Question(Subject, id, author, content, Tags);
        }
    }

    //Getters
    public String getSubject() {
        return Subject;
    }
    public String getAuthor() {
        return author;
    }
    public String getContent() {
        return content;
    }
    public List<String> getTags() {
        return Tags;
    }
}