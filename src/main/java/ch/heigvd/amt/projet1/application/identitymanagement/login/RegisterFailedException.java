package ch.heigvd.amt.projet1.application.identitymanagement.login;

import ch.heigvd.amt.projet1.application.BusinessException;

public class RegisterFailedException extends BusinessException {
    public RegisterFailedException(String message ){super(message);}
}
