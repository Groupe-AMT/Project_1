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

    public void saveAnswer(AnswerCommand command) throws AnswerException {
        try {
            Answer newAnswer = Answer.builder()
                    .author(command.getAuthor())
                    .questionId(command.getQuestionId())
                    .content(command.getContent().replaceAll("'"," "))
                    .build();

            answerRepository.save(newAnswer);
        }catch (Exception e){
            throw new AnswerException(e.getMessage());
        }
    }
    public List<Answer> getRelatedAnswer(QuestionId id){
        List<Answer> relatedAnswer = (List<Answer>) answerRepository.findAllforQuestion(id);
        return relatedAnswer;
    }
}
