package ch.heigvd.amt.projet1.infrastructure.persistence.exception;

public class IntegrityConstaintViolationException extends RuntimeException {
    public IntegrityConstaintViolationException(String s){
        super(s);
    }
}
