package ch.heigvd.amt.projet1.application.commentmanagement;

import ch.heigvd.amt.projet1.domain.Id;
import ch.heigvd.amt.projet1.domain.comment.Comment;
import ch.heigvd.amt.projet1.domain.comment.ICommentRepository;

import java.util.List;

public class CommentManagementFacade {
    private ICommentRepository commentRepository;

    public CommentManagementFacade(ICommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    public int saveComment(CommentCommand command) throws CommentException {
        try {
            Comment newComment = Comment.builder()
                    .author(command.getAuthor())
                    .questionId(command.getId())
                    .answerId(command.getId())
                    .type(command.getType())
                    .content(command.getContent())
                    .build();

            commentRepository.save(newComment);
        }catch (Exception e){
            throw new CommentException(e.getMessage());
        }
        return 1;
    }
    public  List<Comment> getRelatedComment(Id id,String type){
        List<Comment> relatedComment = (List<Comment>) commentRepository.findAllbySource(id,type);
        return relatedComment;
    }
}
