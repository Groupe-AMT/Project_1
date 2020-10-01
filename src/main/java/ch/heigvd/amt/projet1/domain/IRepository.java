package ch.heigvd.amt.projet1.domain;

import java.util.Collection;
import java.util.Optional;

public interface IRepository {
    public void Save(IEntity entity);
    public void Remove(Id id);
    public Optional<IEntity> findById(Id id);
    public Collection<IEntity> findAll();
}
