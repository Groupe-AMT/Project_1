package ch.heigvd.amt.projet1.infrastructure.persistence.memory.ObjectRepository;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
public class ServiceDAO {

    @Inject @Named("PersonDAO")
    JdbcPersonRepository personDao;

    @Inject @Named("QuestionDAO")
    JdbcQuestionRepository questionDAO;
}
