package ch.heigvd.amt.projet1.application.questionmanagement;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import lombok.Value;

import java.util.List;

@Value
@Builder
@EqualsAndHashCode
public class QuestionsDTO {

    @Value
    @Builder
    @EqualsAndHashCode
    public static class QuestionDTO{
        private String subject;
        private String author;
        private String content;
        private List<String> Tags;
    }
    @Singular
    private List<QuestionDTO> questions;
}
