package ch.heigvd.amt.projet1.application.identitymanagement.login;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class RegisterCommand {
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private String clearPassword;
}
