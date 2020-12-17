package ch.heigvd.amt.projet1.application.apimanagement;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Builder
@Getter
public class Event implements Serializable { //fields put to public because field.get() causes exception otherwise
    public String idUser; //UUID of an user
    public String userName; //name of an user
    public String action;
    public String attribute;
    public OffsetDateTime timestamp; //OffsetDateTime.ofInstant(Instant.now(), ZoneOffset.UTC) in tests
}
