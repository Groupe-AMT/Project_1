package ch.heigvd.amt.projet1.application.commentmanagement;

import java.util.ArrayList;
import ch.heigvd.amt.projet1.domain.comment.Comment;
import ch.heigvd.amt.projet1.domain.comment.CommentId;
import ch.heigvd.amt.projet1.domain.comment.ICommentRepository;
import ch.heigvd.amt.projet1.domain.question.QuestionId;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestCommentManagement {
    ICommentRepository AR = mock(ICommentRepository.class);
    CommentManagementFacade AMF = new CommentManagementFacade(AR);
    CommentId id = new CommentId();
    CommentId id2 = new CommentId();
    QuestionId Qid = new QuestionId();
    QuestionId Qid2 = new QuestionId();
    CommentCommand command = CommentCommand.builder()
            .author("Jerome")
            .content("la reponse")
            .type("type")
            .Id("id1")
            .build();
    CommentCommand command2 = CommentCommand.builder()
            .author("Jerome")
            .content("la reponse")
            .type("type")
            .Id("id")
            .build();
    Comment C1 = Comment.builder()
            .author("Jerome")
            .questionId(id.toString())
            .answerId(Qid.toString())
            .type("type")
            .content(command.getContent())
            .build();
    Comment C2 = Comment.builder()
            .author("Jerome")
            .questionId(id2.toString())
            .answerId(Qid2.toString())
            .type("type")
            .content(command.getContent())
            .build();
    List<Comment> list = new ArrayList<>();
    {
        list.add(C1);
    }

    @Test
    void testSaveRepository(){
        when(AR.save(C1)).thenReturn(1);
        try{
            assertEquals(1,AMF.saveComment(command));
        }catch (Exception e){ }
    }

    @Test
    void testSaveRepositoryFail(){
        when(AR.save(anyObject())).thenThrow(Exception.class);
        assertThrows(CommentException.class, ()->AMF.saveComment(command2));
    }

    @Test
    void testgetRelatedAnswer(){
        when(AR.findAllbySource(anyObject(), anyString())).thenReturn(list);
        assertEquals(1, AMF.getRelatedComment(id, "").size());
    }
}
