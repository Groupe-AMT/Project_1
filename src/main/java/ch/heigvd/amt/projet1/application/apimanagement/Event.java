package ch.heigvd.amt.projet1.application.apimanagement;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Builder
@Getter
public class Event implements Serializable {
    private String idUser; //UUID of an user
    private String userName; //name of an user
    private String action;
    private String attribute;
    private OffsetDateTime timestamp; //OffsetDateTime.ofInstant(Instant.now(), ZoneOffset.UTC) in tests
}
