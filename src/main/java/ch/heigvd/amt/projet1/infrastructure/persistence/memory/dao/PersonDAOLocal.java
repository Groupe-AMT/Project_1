package ch.heigvd.amt.projet1.infrastructure.persistence.memory.dao;

import ch.heigvd.amt.projet1.domain.person.Person;
import ch.heigvd.amt.projet1.domain.person.PersonId;

import javax.ejb.Local;
import javax.sql.DataSource;
import java.util.List;

public interface PersonDAOLocal{
    // Interface de gestion de la table Person
    public long save(Person person);

    public long remove(PersonId id);
    public long removeByUsername(String username);

    public Person findById(PersonId id);
    public Person findByUsername(String username);
    public List<Person> findAll();
}
