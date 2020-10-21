package ch.heigvd.amt.projet1.application.commentmanagement;

import ch.heigvd.amt.projet1.application.BusinessException;

public class CommentException extends BusinessException {
    public CommentException(String s) {
        super(s);
    }
}
