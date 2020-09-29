package ch.heigvd.amt.projet1.model;

import java.util.Collection;

public interface IQuestionRepository {
    public Collection<Question> find(QuestionQuery query);
}
