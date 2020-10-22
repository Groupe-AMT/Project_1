package ch.heigvd.amt.projet1.application.commentmanagement;

import ch.heigvd.amt.projet1.domain.Id;
import ch.heigvd.amt.projet1.domain.answer.Answer;
import ch.heigvd.amt.projet1.domain.answer.AnswerId;
import ch.heigvd.amt.projet1.domain.comment.Comment;
import ch.heigvd.amt.projet1.domain.comment.ICommentRepository;
import ch.heigvd.amt.projet1.domain.question.QuestionId;

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

            return commentRepository.save(newComment);
            //return newComment;
        }catch (Exception e){
            throw new CommentException(e.getMessage());
        }
    }

    public  List<Comment> getRelatedComment(Id id,String type){
        List<Comment> relatedComment = (List<Comment>) commentRepository.findAllbySource(id,type);
        return relatedComment;
    }
}
