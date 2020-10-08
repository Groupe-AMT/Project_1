package ch.heigvd.amt.projet1.application;

import ch.heigvd.amt.projet1.application.identitymanagement.IdentityManagementFacade;
import ch.heigvd.amt.projet1.application.identitymanagement.QuestionManagementFacade;
import ch.heigvd.amt.projet1.infrastructure.persistence.memory.dao.PersonDAO;
import ch.heigvd.amt.projet1.infrastructure.persistence.memory.dao.PersonDAOLocal;
import ch.heigvd.amt.projet1.infrastructure.persistence.memory.dao.QuestionDAO;
import ch.heigvd.amt.projet1.infrastructure.persistence.memory.dao.QuestionDAOLocal;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
public class ServiceRegistry {

    @Inject @Named("PersonDAO")
    PersonDAOLocal personRepository;

    @Inject @Named("QuestionDAO")
    QuestionDAOLocal questionRepository;

    private static QuestionManagementFacade questionFacade;
    private static IdentityManagementFacade identityFacade;

    public static QuestionManagementFacade getQuestionFacade(){
        return questionFacade;
    }
    public static IdentityManagementFacade getIdentityFacade(){
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


