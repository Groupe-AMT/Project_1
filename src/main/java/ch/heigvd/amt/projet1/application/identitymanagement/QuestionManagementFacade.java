package ch.heigvd.amt.projet1.application.identitymanagement;

import ch.heigvd.amt.projet1.application.questionmanagement.QuestionCommand;
import ch.heigvd.amt.projet1.application.questionmanagement.QuestionsDTO;
import ch.heigvd.amt.projet1.application.questionmanagement.QuestionException;
import ch.heigvd.amt.projet1.domain.IQuestionRepository;
import ch.heigvd.amt.projet1.domain.question.Question;
import ch.heigvd.amt.projet1.domain.question.QuestionQuery;
import org.apache.taglibs.standard.lang.jstl.GreaterThanOperator;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionManagementFacade {
    private IQuestionRepository questionRepository ;
    public QuestionManagementFacade(IQuestionRepository questionRepository){this.questionRepository=questionRepository;}
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
    public QuestionsDTO getQuestions(QuestionQuery query){
        Collection<Question> allQuestions = questionRepository.findAll();
        List<QuestionsDTO.QuestionDTO> allQuestionsDTO = allQuestions.stream().map(question -> QuestionsDTO.QuestionDTO.builder()
        .author(question.getAuthor())
        .content(question.getContent())
        .subject(question.getSubject())
        .Tags(question.getTags())
        .build()).collect(Collectors.toList());
        return QuestionsDTO.builder().questions(allQuestionsDTO).build();
    }
}
