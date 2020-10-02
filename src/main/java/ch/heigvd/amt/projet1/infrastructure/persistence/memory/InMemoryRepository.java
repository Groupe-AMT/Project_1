package ch.heigvd.amt.projet1.infrastructure.persistence.memory;

import ch.heigvd.amt.projet1.domain.IEntity;
import ch.heigvd.amt.projet1.domain.IRepository;
import ch.heigvd.amt.projet1.domain.Id;
import ch.heigvd.amt.projet1.domain.person.PersonId;

import javax.swing.text.html.parser.Entity;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public abstract class InMemoryRepository<ENTITY extends IEntity<ENTITY,ID>,ID extends Id>implements IRepository<ENTITY,ID> {
private Map<ID,ENTITY> store = new ConcurrentHashMap<>();

    @Override
    public void save(ENTITY entity) {
        PersonId a = (PersonId) entity.getId();
        store.put(entity.getId(),entity);
    }

    @Override
    public void remove(ID id) {
        store.remove(id);
    }

    public Optional<ENTITY> findById(ID id) {
        ENTITY existingEntity = store.get(id);
        if(existingEntity==null){
            return Optional.empty();
        }
        ENTITY cloneEntity = existingEntity.deepClone();
        return Optional.of(cloneEntity);
    }
    public Collection<ENTITY> findAll(){
        return store.values().stream().map(entity -> entity.deepClone()).collect(Collectors.toList());
    }
}
