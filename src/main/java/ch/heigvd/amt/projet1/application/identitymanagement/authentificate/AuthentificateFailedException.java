package ch.heigvd.amt.projet1.application.identitymanagement.authentificate;

import ch.heigvd.amt.projet1.application.BusinessException;

public class AuthentificateFailedException extends BusinessException {
    public AuthentificateFailedException(String message){super(message); }
}
