package ch.heigvd.amt.projet1.application.questionmanagement;

import ch.heigvd.amt.projet1.domain.question.IQuestionRepository;
import ch.heigvd.amt.projet1.domain.question.Question;

import java.util.List;

public class QuestionManagementFacade {
    private IQuestionRepository questionRepository;

    public QuestionManagementFacade(IQuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }

    public void saveQuestion(QuestionCommand command) throws QuestionException{
        try {
            questionRepository.save(Question.builder()
                    .author(command.getAuthor())
                    .Subject(command.getSubject().replaceAll("'"," "))
                    .Tags(command.getTags())
                    .content(command.getContent().replaceAll("'"," "))
                    .build());
        }catch (Exception e){
            throw new QuestionException(e.getMessage());
        }
    }
    public List<Question> getQuestions(){
        return (List<Question>) questionRepository.findAll();
    }

    public List<Question> getPageQuestions(int currentPage, int nbPerPage){
        return questionRepository.findPageQuestion(currentPage, nbPerPage);
    }

    public List<Question> getFilteredPageQuestions(int currentPage, int nbPerPage, List<String> Tags){
        return questionRepository.FilterfindPageQuestion(currentPage, nbPerPage, Tags);
    }
}
