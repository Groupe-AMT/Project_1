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
        List<Question> allQuestions = (List<Question>) questionRepository.findAll();
        return allQuestions;
    }

    public List<Question> getPageQuestions(int currentPage, int nbPerPage){
        List<Question> pageQuestions = (List<Question>) questionRepository.findPageQuestion(currentPage, nbPerPage);
        return pageQuestions;
    }

    public List<Question> getFilteredPageQuestions(int currentPage, int nbPerPage, List<String> Tags){
        List<Question> pageQuestions = (List<Question>) questionRepository.FilterfindPageQuestion(currentPage, nbPerPage, Tags);
        return pageQuestions;
    }
}
