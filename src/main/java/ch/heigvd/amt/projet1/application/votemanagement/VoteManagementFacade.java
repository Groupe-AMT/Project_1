package ch.heigvd.amt.projet1.application.votemanagement;

import ch.heigvd.amt.projet1.domain.Id;
import ch.heigvd.amt.projet1.domain.comment.Comment;
import ch.heigvd.amt.projet1.domain.comment.ICommentRepository;

import java.util.List;

public class CommentManagementFacade {
    private ICommentRepository commentRepository;

    public CommentManagementFacade(ICommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    public void saveVote(VoteCommand command) throws VoteException {
        try {
            Vote newVote = Vote.builder()
                    .author(command.getAuthor())
                    .questionId(command.getId())
                    .answerId(command.getId())
                    .type(command.getType())
                    .content(command.getContent())
                    .build();

            commentRepository.save(newVote);
        }catch (Exception e){
            throw new VoteException(e.getMessage());
        }
    }
    public  List<Comment> getRelatedVote(Id id,String type){
        List<Comment> relatedComment = (List<Comment>) commentRepository.findAllbySource(id,type);
        return relatedComment;
    }
}
