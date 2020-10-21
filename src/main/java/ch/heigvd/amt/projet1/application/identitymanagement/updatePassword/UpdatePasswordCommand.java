package ch.heigvd.amt.projet1.application.identitymanagement.updatePassword;

import lombok.*;

@Builder
@Getter
@EqualsAndHashCode
// public pour effectuer les tests
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class UpdatePasswordCommand {
    private String prev_pass;
    private String new_pass;
}
