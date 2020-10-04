package ch.heigvd.amt.projet1.infrastructure.persistence.memory.dao;

import ch.heigvd.amt.projet1.domain.person.Person;
import ch.heigvd.amt.projet1.domain.person.PersonId;
import ch.heigvd.amt.projet1.domain.question.Question;
import ch.heigvd.amt.projet1.domain.question.QuestionId;

import javax.ejb.Local;
import java.util.List;

@Local
public interface QuestionDAOLocal {
    // Interface de gestion de la table Question
    public long save(Question question);

    public long remove(Question id);

    public Question findById(QuestionId id);
    public List<Question> findAll();
}
