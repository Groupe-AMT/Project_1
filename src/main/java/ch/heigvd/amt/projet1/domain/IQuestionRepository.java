package ch.heigvd.amt.projet1.domain;

import ch.heigvd.amt.projet1.domain.question.Question;

import java.util.Collection;

public interface IQuestionRepository {
    public Collection<Question> findByTag(String tag);
    public Collection<Question> findByAuthor(String author);
}
