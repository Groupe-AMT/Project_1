package ch.heigvd.amt.projet1.infrastructure.persistence.memory.dao;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
public class ServiceDAO {

    @Inject @Named("PersonDAO")
    PersonDAO personDao;

    @Inject @Named("QuestionDAO")
    QuestionDAO questionDAO;
}
