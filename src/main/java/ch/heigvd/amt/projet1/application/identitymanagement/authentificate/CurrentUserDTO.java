package ch.heigvd.amt.projet1.application.identitymanagement.authentificate;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CurrentUserDTO {
    private String username;
    private String firstname;
    private String lastname;
    private String email;
}
