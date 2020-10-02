package ch.heigvd.amt.projet1.application.questionmanagement;

import ch.heigvd.amt.projet1.application.BusinessException;

public class QuestionException extends BusinessException {
    public QuestionException(String s) {
        super(s);
    }
}
