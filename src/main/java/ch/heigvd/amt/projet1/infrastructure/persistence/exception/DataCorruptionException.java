package ch.heigvd.amt.projet1.infrastructure.persistence.exception;

public class DataCorruptionException extends RuntimeException{
    public DataCorruptionException(String s){
        super(s);
    }
}
