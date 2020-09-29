package ch.heigvd.amt.projet1.domain;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.Null;
import java.util.UUID;
@EqualsAndHashCode
public abstract class Id {
    private UUID id;
    public Id(String id){this.id = UUID.fromString(id);}
    public Id(){id = UUID.randomUUID();}
    public Id (UUID id){
        if(id ==null){
            throw new NullPointerException();
        }
        this.id=id;
    }
    public String asString(){return id.toString();}

}
