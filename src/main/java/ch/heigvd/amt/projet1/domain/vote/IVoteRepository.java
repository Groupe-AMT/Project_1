package ch.heigvd.amt.projet1.domain.vote;

import ch.heigvd.amt.projet1.domain.IRepository;
import ch.heigvd.amt.projet1.domain.Id;

public interface IVoteRepository extends IRepository<Vote, VoteId> {
    public int count(Id id, String type,boolean up);

}
