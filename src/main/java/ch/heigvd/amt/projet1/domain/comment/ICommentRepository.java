package ch.heigvd.amt.projet1.domain.comment;

import ch.heigvd.amt.projet1.domain.IRepository;
import ch.heigvd.amt.projet1.domain.Id;

import java.util.List;

public interface ICommentRepository extends IRepository<Comment, CommentId> {
    public List<Comment> findAllbySource(Id id, String type);

    //Statistics
    public int SizeFor(String username);
}
