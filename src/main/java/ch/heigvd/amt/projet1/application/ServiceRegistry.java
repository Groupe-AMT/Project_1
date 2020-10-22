package ch.heigvd.amt.projet1.application;

import ch.heigvd.amt.projet1.application.answermanagement.AnswerManagementFacade;
import ch.heigvd.amt.projet1.application.commentmanagement.CommentManagementFacade;
import ch.heigvd.amt.projet1.application.identitymanagement.IdentityManagementFacade;
import ch.heigvd.amt.projet1.application.questionmanagement.QuestionManagementFacade;
import ch.heigvd.amt.projet1.application.statisticmanagement.StatisticManagementFacade;
import ch.heigvd.amt.projet1.application.votemanagement.VoteManagementFacade;
import ch.heigvd.amt.projet1.domain.vote.Vote;
import ch.heigvd.amt.projet1.infrastructure.persistence.memory.ObjectRepository.*;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped //  @stateless
public class ServiceRegistry {
    @Inject
    JdbcPersonRepository personRepository;

    @Inject
    JdbcQuestionRepository questionRepository;

    @Inject
    JdbcAnswerRepository answerRepository;

    @Inject
    JdbcCommentRepository commentRepository;
    @Inject
    JdbcVoteRepository voteRepository;

    private QuestionManagementFacade questionFacade;
    private IdentityManagementFacade identityFacade;
    private AnswerManagementFacade answerFacade;
    private CommentManagementFacade commentFacade;
    private VoteManagementFacade voteFacade;
    //Statistics
    private StatisticManagementFacade statisticFacade;

    @PostConstruct
    // la méthode est appelée
    void init (){
        identityFacade = new IdentityManagementFacade(personRepository);
        questionFacade = new QuestionManagementFacade(questionRepository);
        answerFacade = new AnswerManagementFacade(answerRepository);
        commentFacade = new CommentManagementFacade(commentRepository);
        voteFacade = new VoteManagementFacade(voteRepository);
        statisticFacade = new StatisticManagementFacade(questionRepository, answerRepository, commentRepository, personRepository);

    }

    public QuestionManagementFacade getQuestionFacade(){
        return questionFacade;
    }
    public AnswerManagementFacade getAnswerFacade(){
        return answerFacade;
    }
    public IdentityManagementFacade getIdentityFacade(){
        return identityFacade;
    }
    public CommentManagementFacade getCommentFacade(){
        return commentFacade;
    }
    public VoteManagementFacade getVoteFacade(){ return voteFacade; }
    public StatisticManagementFacade getStatisticFacade(){
        return statisticFacade;
    }
}


