package ch.heigvd.amt.projet1.application.identitymanagement.updateProfile;

import lombok.*;

@Builder
@Getter
@EqualsAndHashCode
// public pour effectuer les tests
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class UpdateProfileCommand {
    private String username;
    private String firstname;
    private String lastname;
    private String email;
}
