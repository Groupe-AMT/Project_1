package ch.heigvd.amt.projet1.domain;

import java.util.UUID;

public abstract class Id {
    private UUID id; //id unique
    public UUID getId(){
        return id;
    }
    public void setId(UUID id1){
        this.id = id1;
    }
    public Id(){ //donne un id aléatoire et unique
        this.id = UUID.randomUUID();
    }
    public Id(String string_id){ //crée un id unique à partir d'une chaine de caractère
        UUID.fromString(string_id);
    }

    public String asString(){
        return this.id.toString();
    }

}
