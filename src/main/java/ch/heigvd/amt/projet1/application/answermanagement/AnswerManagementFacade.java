package ch.heigvd.amt.projet1.application.answermanagement;

import ch.heigvd.amt.projet1.domain.answer.Answer;
import ch.heigvd.amt.projet1.domain.answer.IAnswerRepository;
import ch.heigvd.amt.projet1.domain.question.QuestionId;
import java.util.List;

public class AnswerManagementFacade {
    private IAnswerRepository answerRepository;

    public AnswerManagementFacade(IAnswerRepository answerRepository){
        this.answerRepository = answerRepository;
    }

    public double saveAnswer(AnswerCommand command) throws AnswerException {
        try {
            answerRepository.save(Answer.builder()
                    .author(command.getAuthor())
                    .questionId(command.getQuestionId())
                    .content(command.getContent().replaceAll("'"," "))
                    .build());
        }catch (Exception e){
            throw new AnswerException(e.getMessage());
        }
        return 1;
    }
    public List<Answer> getRelatedAnswer(QuestionId id){
        return answerRepository.findAllforQuestion(id);
    }
}
