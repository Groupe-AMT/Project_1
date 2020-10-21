package ch.heigvd.amt.projet1.domain.answer;

import org.junit.jupiter.api.RepeatedTest;
import static org.junit.jupiter.api.Assertions.*;

public class TestAnswer {
    Answer newAnswer = Answer.builder()
            .author("Jerome")
            .questionId("1")
            .content("Ceci est une question")
            .build();

    @RepeatedTest(20)
    void testUpVote(){
        int tmp = newAnswer.getVote();
        newAnswer.upVote();
        assertEquals((tmp + 1), newAnswer.getVote());
    }

    @RepeatedTest(20)
    void testDownVote(){
        int tmp = newAnswer.getVote();
        newAnswer.downVote();
        assertEquals((tmp - 1), newAnswer.getVote());
    }
}
