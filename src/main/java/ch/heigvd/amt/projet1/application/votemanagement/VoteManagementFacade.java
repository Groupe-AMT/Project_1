package ch.heigvd.amt.projet1.application.votemanagement;

import ch.heigvd.amt.projet1.domain.Id;
import ch.heigvd.amt.projet1.domain.vote.IVoteRepository;
import ch.heigvd.amt.projet1.domain.vote.Vote;

public class VoteManagementFacade {
    private IVoteRepository voteRepository;

    public VoteManagementFacade(IVoteRepository voteRepository){
        this.voteRepository = voteRepository;
    }

    public void saveVote(VoteCommand command) throws VoteException {
        try {
            Vote newVote = Vote.builder()
                    .author(command.getAuthor())
                    .questionId(command.getId())
                    .answerId(command.getId())
                    .type(command.getType())
                    .note(command.isNote())
                    .build();

            voteRepository.save(newVote);
        }catch (Exception e){
            throw new VoteException(e.getMessage());
        }
    }
    public int count(Id id,String type,boolean up){
        return voteRepository.count(id,type,up);
    }
}
