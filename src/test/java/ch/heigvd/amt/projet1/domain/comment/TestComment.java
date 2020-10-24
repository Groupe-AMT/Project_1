package ch.heigvd.amt.projet1.domain.comment;

import org.junit.jupiter.api.RepeatedTest;
import static org.junit.jupiter.api.Assertions.*;

public class TestComment {
    Comment newComment = Comment.builder()
            .author("jerome")
            .questionId("1")
            .answerId("2")
            .type("type")
            .content("commentaire")
            .build();

    @RepeatedTest(10)
    void testUpVote(){
        int tmp = newComment.getVote();
        newComment.upVote();
        assertEquals((tmp + 1), newComment.getVote());
    }

    @RepeatedTest(10)
    void testDownVote(){
        int tmp = newComment.getVote();
        newComment.downVote();
        assertEquals((tmp - 1), newComment.getVote());
    }
}
