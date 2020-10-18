package ch.heigvd.amt.projet1.domain.answer;

import ch.heigvd.amt.projet1.domain.IRepository;
import ch.heigvd.amt.projet1.domain.answer.Answer;
import ch.heigvd.amt.projet1.domain.answer.AnswerId;
import ch.heigvd.amt.projet1.domain.person.Person;
import ch.heigvd.amt.projet1.domain.person.PersonId;
import ch.heigvd.amt.projet1.domain.question.Question;
import ch.heigvd.amt.projet1.domain.question.QuestionId;

import javax.ejb.Local;
import java.util.List;

public interface IAnswerRepository extends IRepository<Answer, AnswerId> {
    public List<Answer> findAllforQuestion(QuestionId id);
}
