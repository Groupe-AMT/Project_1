package ch.heigvd.amt.projet1.application;

import ch.heigvd.amt.projet1.application.identitymanagement.IdentityManagementFacade;
import ch.heigvd.amt.projet1.application.questionmanagement.QuestionManagementFacade;
import ch.heigvd.amt.projet1.domain.question.IQuestionRepository;
import ch.heigvd.amt.projet1.domain.person.IPersonRepository;
import ch.heigvd.amt.projet1.infrastructure.persistence.memory.dao.PersonDAOLocal;
import ch.heigvd.amt.projet1.infrastructure.persistence.memory.dao.QuestionDAOLocal;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ServiceRegistry {
    @Inject
    PersonDAOLocal personRepository;

    @Inject
    QuestionDAOLocal questionRepository;

    private QuestionManagementFacade questionFacade = new QuestionManagementFacade();
    private IdentityManagementFacade identityFacade = new IdentityManagementFacade();

    public QuestionManagementFacade getQuestionFacade(){
        return questionFacade;
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


