package ch.heigvd.amt.projet1.domain.question;

import ch.heigvd.amt.projet1.domain.IRepository;
import ch.heigvd.amt.projet1.domain.question.Question;
import ch.heigvd.amt.projet1.domain.question.QuestionId;

import java.util.Collection;
import java.util.List;

public interface IQuestionRepository extends IRepository<Question, QuestionId> {
    public Collection<Question> findByTag(String tag);
    public Collection<Question> findByAuthor(String author);

    public List<Question> findPageQuestion(int currentPage, int nbPerPage);

    //Statistics
    public int SizeFor(String username);
}
