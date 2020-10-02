package ch.heigvd.amt.projet1.application.questionmanagement;

import ch.heigvd.amt.projet1.domain.person.Person;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class QuestionCommand {
    private String subject;
    private String author;
    private String content;
    private List<String> tags;
}
