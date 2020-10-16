package ch.heigvd.amt.projet1.infrastructure.persistence.memory;

import ch.heigvd.amt.projet1.domain.IEntity;
import ch.heigvd.amt.projet1.domain.IRepository;
import ch.heigvd.amt.projet1.domain.Id;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public abstract class InMemoryRepository<ENTITY extends IEntity<ENTITY,ID>,ID extends Id>implements IRepository<ENTITY,ID> {
    private Map<ID,ENTITY> store = new ConcurrentHashMap<>();

    @Override
    public int save(ENTITY entity) {
        store.put(entity.getId(),entity);
        return 0;
    }

    @Override
    public int remove(ID id) {
        store.remove(id);
        return 0;
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
