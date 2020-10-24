package ch.heigvd.amt.projet1.application.answermanagement;

import ch.heigvd.amt.projet1.domain.question.QuestionId;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class AnswerCommand {
    private String author;
    private String content;
    private String questionId;
}
