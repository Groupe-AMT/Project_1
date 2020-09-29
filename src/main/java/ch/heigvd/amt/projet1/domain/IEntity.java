package ch.heigvd.amt.projet1.domain;

public interface IEntity<ENTITY extends IEntity, ID extends Id> {
    ID getId();
    ENTITY deepClone();
}
