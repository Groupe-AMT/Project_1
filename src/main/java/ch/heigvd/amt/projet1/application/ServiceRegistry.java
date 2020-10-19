package ch.heigvd.amt.projet1.application;

import ch.heigvd.amt.projet1.application.identitymanagement.IdentityManagementFacade;
import ch.heigvd.amt.projet1.application.questionmanagement.QuestionManagementFacade;
import ch.heigvd.amt.projet1.application.statisticmanagement.StatisticManagementFacade;
import ch.heigvd.amt.projet1.infrastructure.persistence.memory.ObjectRepository.JdbcAnswerRepository;
import ch.heigvd.amt.projet1.infrastructure.persistence.memory.ObjectRepository.JdbcCommentRepository;
import ch.heigvd.amt.projet1.infrastructure.persistence.memory.ObjectRepository.JdbcPersonRepository;
import ch.heigvd.amt.projet1.infrastructure.persistence.memory.ObjectRepository.JdbcQuestionRepository;

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


    private QuestionManagementFacade questionFacade;
    private IdentityManagementFacade identityFacade;

    //Statistics
    private StatisticManagementFacade statisticFacade;

    @PostConstruct
    // la méthode est appelée
    void init (){
        identityFacade = new IdentityManagementFacade(personRepository);
        questionFacade = new QuestionManagementFacade(questionRepository);

        statisticFacade = new StatisticManagementFacade(questionRepository, answerRepository, commentRepository, personRepository);
    }

    public QuestionManagementFacade getQuestionFacade(){
        return questionFacade;
    }
    public IdentityManagementFacade getIdentityFacade(){
        return identityFacade;
    }

    public StatisticManagementFacade getStatisticFacade(){
        return statisticFacade;
    }

    /*
    private static ServiceRegistry singleton;
    private static PersonDAOLocal personRepository;
    private static IdentityManagementFacade identityManagementFacade;
    public static ServiceRegistry getServiceRegistry(){
        if(singleton==null){
            singleton = new ServiceRegistry();
        }
        return singleton;
    }
    private ServiceRegistry(){
        singleton=this;
        personRepository= new PersonDAO();
        identityManagementFacade=new IdentityManagementFacade(personRepository);
    }
    public  IdentityManagementFacade getIdentityManagementFacade(){return  identityManagementFacade;}
    */
}


