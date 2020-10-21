package ch.heigvd.amt.projet1.application.identitymanagement.updatePassword;

import ch.heigvd.amt.projet1.application.BusinessException;

public class UpdatePasswordFailedException extends BusinessException {
    public UpdatePasswordFailedException(String message ){super(message);}
}
