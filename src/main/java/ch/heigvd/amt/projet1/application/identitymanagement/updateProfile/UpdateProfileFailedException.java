package ch.heigvd.amt.projet1.application.identitymanagement.updateProfile;

import ch.heigvd.amt.projet1.application.BusinessException;

public class UpdateProfileFailedException extends BusinessException {
    public UpdateProfileFailedException(String message ){super(message);}
}
