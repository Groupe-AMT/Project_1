package ch.heigvd.amt.projet1.application;

import ch.heigvd.amt.projet1.application.identitymanagement.IdentityManagementFacade;
import ch.heigvd.amt.projet1.application.identitymanagement.QuestionManagementFacade;
import ch.heigvd.amt.projet1.domain.IQuestionRepository;
import ch.heigvd.amt.projet1.domain.person.IPersonRepository;
import ch.heigvd.amt.projet1.infrastructure.persistence.memory.InMemoryPersonRepository;
import ch.heigvd.amt.projet1.infrastructure.persistence.memory.InMemoryQuestionRepository;

public class ServiceQuestion {
    private static ServiceQuestion singleton;
    private static IQuestionRepository questionRepository;
    private static  QuestionManagementFacade questionManagementFacade;
    public static ServiceQuestion getServiceQuestion(){
        if(singleton==null){
            singleton = new ServiceQuestion();
        }
        return singleton;
    }
    private ServiceQuestion(){
        singleton=this;
        questionRepository= new InMemoryQuestionRepository();
        questionManagementFacade = new QuestionManagementFacade(questionRepository);
    }
    public QuestionManagementFacade getQuestionManagementFacade(){return  questionManagementFacade;}
}
