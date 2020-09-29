package ch.heigvd.amt.projet1.model;

import java.util.UUID;

public abstract class Id {
    private UUID id;
    public UUID getId(){
        return id;
    }
    public void setId(UUID id1){
        this.id = id1;
    }
    public Id(){
        this.id = UUID.randomUUID();
    }
    public Id(String string_id){
        UUID.fromString(string_id);
    }

    public String asString(){
        return this.id.toString();
    }

}
