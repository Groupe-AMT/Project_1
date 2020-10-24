package ch.heigvd.amt.projet1.application.votemanagement;

import ch.heigvd.amt.projet1.application.BusinessException;

public class VoteException extends BusinessException {
    public VoteException(String s) {
        super(s);
    }
}
