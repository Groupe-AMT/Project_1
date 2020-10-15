package ch.heigvd.amt.projet1.application.identitymanagement.login;

import lombok.*;

@Builder
@Getter
@EqualsAndHashCode
// public pour effectuer les tests
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class RegisterCommand {
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private String clearPassword;
}
