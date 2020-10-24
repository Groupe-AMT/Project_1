package ch.heigvd.amt.projet1.application.statisticmanagement;

import ch.heigvd.amt.projet1.domain.answer.IAnswerRepository;
import ch.heigvd.amt.projet1.domain.comment.ICommentRepository;
import ch.heigvd.amt.projet1.domain.person.IPersonRepository;
import ch.heigvd.amt.projet1.domain.question.IQuestionRepository;
import ch.heigvd.amt.projet1.domain.question.Question;
import ch.heigvd.amt.projet1.domain.statistic.Statistic;

public class StatisticManagementFacade {
    private IQuestionRepository questionRepository;
    private IAnswerRepository answerRepository;
    private ICommentRepository commentRepository;
    private IPersonRepository personRepository;

    public StatisticManagementFacade(IQuestionRepository q, IAnswerRepository a, ICommentRepository c, IPersonRepository p){
        questionRepository = q;
        answerRepository = a;
        commentRepository = c;
        personRepository = p;
    }

    public Statistic getStats(String username){
        return Statistic.builder()
                .nbQuestions(questionRepository.Size())
                .nbAnswers(answerRepository.Size())
                .nbComments(commentRepository.Size())
                .nbUsers(personRepository.Size())
                .nbSelfQuestions(questionRepository.SizeFor(username))
                .nbSelfAnswers(answerRepository.SizeFor(username))
                .nbSelfComments(commentRepository.SizeFor(username))
                .build();
    }

    public int getQuestionSize(){
        return questionRepository.Size();
    }
}
