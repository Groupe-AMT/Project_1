package ch.heigvd.amt.projet1.application;

import ch.heigvd.amt.projet1.application.identitymanagement.IdentityManagementFacade;
import ch.heigvd.amt.projet1.application.questionmanagement.QuestionManagementFacade;
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

    private QuestionManagementFacade questionFacade;
    private IdentityManagementFacade identityFacade;

    @PostConstruct
    // la méthode est appelée
    void init (){
        identityFacade = new IdentityManagementFacade(personRepository);
        questionFacade = new QuestionManagementFacade(questionRepository);
    }

    public QuestionManagementFacade getQuestionFacade(){
        return questionFacade;
    }
    public IdentityManagementFacade getIdentityFacade(){
        return identityFacade;
    }
}


