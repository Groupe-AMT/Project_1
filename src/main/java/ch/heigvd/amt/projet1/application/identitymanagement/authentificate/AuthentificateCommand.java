package ch.heigvd.amt.projet1.application.identitymanagement.authentificate;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class AuthentificateCommand {
    private String username;
    private String clearPassword;
}
