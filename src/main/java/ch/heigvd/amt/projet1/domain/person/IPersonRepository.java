package ch.heigvd.amt.projet1.domain.person;

import ch.heigvd.amt.projet1.domain.IRepository;

import java.util.Optional;
public interface IPersonRepository extends IRepository<Person,PersonId> {
    public Optional<Person> findByUsername(String username);
}
