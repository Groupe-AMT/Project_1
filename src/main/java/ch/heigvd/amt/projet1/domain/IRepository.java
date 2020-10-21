package ch.heigvd.amt.projet1.domain;

import java.util.Collection;
import java.util.Optional;

public interface IRepository< ENTITY,ID> {
    public int save (ENTITY entity);
    public int remove(ID id);
    public  Optional<ENTITY> findById(ID id);
    public Collection<ENTITY> findAll();

    //Statistics
    public int Size();
}
