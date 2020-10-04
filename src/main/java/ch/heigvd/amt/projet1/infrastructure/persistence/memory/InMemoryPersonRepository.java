package ch.heigvd.amt.projet1.infrastructure.persistence.memory;

import ch.heigvd.amt.projet1.domain.person.IPersonRepository;
import ch.heigvd.amt.projet1.domain.person.Person;
import ch.heigvd.amt.projet1.domain.person.PersonId;
import ch.heigvd.amt.projet1.infrastructure.persistence.exception.DataCorruptionException;
import ch.heigvd.amt.projet1.infrastructure.persistence.exception.IntegrityConstaintViolationException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/*
public class InMemoryPersonRepository extends InMemoryRepository<Person, PersonId> implements IPersonRepository {
    @Override
    public Optional<Person> findByUsername(String username) {
        List<Person> matchingEntities = findAll().stream()
                .filter(person ->person.getUsername().equals(username))
                .collect(Collectors.toList());
        if(matchingEntities.size()<1){
            return Optional.empty();
        }
        if(matchingEntities.size()>1){
            throw new DataCorruptionException("Data store corrupted");
        }
        return Optional.of(matchingEntities.get(0).deepClone());
    }

    @Override
    public void save(Person entity) {
        synchronized (entity.getUsername()){
            if(findByUsername(entity.getUsername()).isPresent()){
                throw new IntegrityConstaintViolationException("Cannot save/update person, integrity constaint violation username already used");
            }
            super.save(entity);
        }
    }

}
*/