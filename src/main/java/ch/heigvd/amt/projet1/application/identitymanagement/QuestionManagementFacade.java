package ch.heigvd.amt.projet1.application.identitymanagement;

import ch.heigvd.amt.projet1.application.questionmanagement.QuestionCommand;
import ch.heigvd.amt.projet1.application.questionmanagement.QuestionsDTO;
import ch.heigvd.amt.projet1.application.questionmanagement.QuestionException;
import ch.heigvd.amt.projet1.domain.IQuestionRepository;
import ch.heigvd.amt.projet1.domain.question.Question;
import ch.heigvd.amt.projet1.domain.question.QuestionQuery;
import ch.heigvd.amt.projet1.infrastructure.persistence.memory.dao.PersonDAO;
import ch.heigvd.amt.projet1.infrastructure.persistence.memory.dao.PersonDAOLocal;
import ch.heigvd.amt.projet1.infrastructure.persistence.memory.dao.QuestionDAO;
import ch.heigvd.amt.projet1.infrastructure.persistence.memory.dao.QuestionDAOLocal;
import org.apache.taglibs.standard.lang.jstl.GreaterThanOperator;

import javax.ejb.EJB;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionManagementFacade {
    @EJB
    private QuestionDAOLocal questionRepository = new QuestionDAO();

    // public QuestionManagementFacade(IQuestionRepository questionRepository){this.questionRepository=questionRepository;}
    public void saveQuestion(QuestionCommand command) throws QuestionException{
        try {
            Question newQuestion = Question.builder()
                    .author(command.getAuthor())
                    .Subject(command.getSubject())
                    .Tags(command.getTags())
                    .content(command.getContent())
                    .build();
            questionRepository.save(newQuestion);
        }catch (Exception e){
            throw new QuestionException(e.getMessage());
        }
    }
    public List<Question> getQuestions(){
        List<Question> allQuestions = questionRepository.findAll();
        return allQuestions;
    }
}
