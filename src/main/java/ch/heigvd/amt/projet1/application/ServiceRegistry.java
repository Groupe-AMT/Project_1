package ch.heigvd.amt.projet1.application;

import ch.heigvd.amt.projet1.application.identitymanagement.IdentityManagementFacade;
import ch.heigvd.amt.projet1.application.questionmanagement.QuestionManagementFacade;
import ch.heigvd.amt.projet1.domain.question.IQuestionRepository;
import ch.heigvd.amt.projet1.domain.person.IPersonRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
public class ServiceRegistry {

    @Inject @Named("DbQuestionRepository")
    IPersonRepository personRepository;

    @Inject @Named("DbPersonRepository")
    IQuestionRepository questionRepository;

    private static QuestionManagementFacade questionFacade;
    private static IdentityManagementFacade identityFacade;

    public QuestionManagementFacade getQuestionFacade(){
        return new QuestionManagementFacade(questionRepository);
    }
    public IdentityManagementFacade getIdentityFacade(){
        return new IdentityManagementFacade(personRepository);
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


