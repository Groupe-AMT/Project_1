package ch.heigvd.amt.projet1.application;

import ch.heigvd.amt.projet1.application.answermanagement.AnswerManagementFacade;
import ch.heigvd.amt.projet1.application.identitymanagement.IdentityManagementFacade;
import ch.heigvd.amt.projet1.application.questionmanagement.QuestionManagementFacade;
import ch.heigvd.amt.projet1.infrastructure.persistence.memory.ObjectRepository.JdbcAnswerRepository;
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
    private QuestionManagementFacade questionFacade;
    private IdentityManagementFacade identityFacade;
    private AnswerManagementFacade answerFacade;

    @PostConstruct
    // la méthode est appelée
    void init (){
        identityFacade = new IdentityManagementFacade(personRepository);
        questionFacade = new QuestionManagementFacade(questionRepository);
        answerFacade = new AnswerManagementFacade(answerRepository);
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


