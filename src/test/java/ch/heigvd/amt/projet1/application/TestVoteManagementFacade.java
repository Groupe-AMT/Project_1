package ch.heigvd.amt.projet1.application;

import ch.heigvd.amt.projet1.application.answermanagement.AnswerException;
import ch.heigvd.amt.projet1.application.votemanagement.VoteCommand;
import ch.heigvd.amt.projet1.application.votemanagement.VoteException;
import ch.heigvd.amt.projet1.application.votemanagement.VoteManagementFacade;
import ch.heigvd.amt.projet1.domain.answer.Answer;
import ch.heigvd.amt.projet1.domain.answer.AnswerId;
import ch.heigvd.amt.projet1.domain.question.QuestionId;
import ch.heigvd.amt.projet1.domain.vote.IVoteRepository;
import ch.heigvd.amt.projet1.domain.vote.Vote;
import ch.heigvd.amt.projet1.domain.vote.VoteId;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestVoteManagementFacade {
    IVoteRepository IVR = mock(IVoteRepository.class);
    VoteManagementFacade VMF = new VoteManagementFacade(IVR);
    QuestionId Qid = new QuestionId();
    AnswerId Aid = new AnswerId();
    Vote newVote = Vote.builder()
            .author("jerome")
            .questionId(Qid.toString())
            .answerId(Aid.toString())
            .type("type")
            .note(true)
            .build();
    VoteCommand voteCommand = VoteCommand.builder()
            .author("jerome")
            .Id((new VoteId()).toString())
            .type("type")
            .note(true)
            .build();

    @Test
    void testSave(){
        when(IVR.save(newVote)).thenReturn(1);
        try{
            assertDoesNotThrow(()->VMF.saveVote(voteCommand));
        }catch (Exception e){ }
    }

    @Test
    void testSaveFail(){
        when(IVR.save(anyObject())).thenThrow(Exception.class);
        assertThrows(VoteException.class, ()->VMF.saveVote(voteCommand));
    }

    @Test
    void testCount(){
        when(IVR.count(anyObject(), anyString(), anyBoolean())).thenReturn(20);
        assertEquals(20, VMF.count(new VoteId(), "type", true));
    }
}
