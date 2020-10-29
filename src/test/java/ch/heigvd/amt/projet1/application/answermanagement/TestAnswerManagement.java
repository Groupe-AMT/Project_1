package ch.heigvd.amt.projet1.application.answermanagement;

import ch.heigvd.amt.projet1.domain.answer.Answer;
import ch.heigvd.amt.projet1.domain.answer.IAnswerRepository;
import ch.heigvd.amt.projet1.domain.question.QuestionId;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestAnswerManagement {
    IAnswerRepository AR = mock(IAnswerRepository.class);
    AnswerManagementFacade AMF = new AnswerManagementFacade(AR);
    QuestionId id = new QuestionId();
    QuestionId id2 = new QuestionId();
    AnswerCommand command = AnswerCommand.builder()
            .author("jerome")
            .content("content")
            .questionId(id.toString())
            .build();
    AnswerCommand command2 = AnswerCommand.builder()
            .author("jerome")
            .content("content")
            .questionId(id2.toString())
            .build();
    Answer newAnswer1 = Answer.builder()
            .author("jerome")
            .questionId(id.toString())
            .content("content")
            .build();
    Answer newAnswer2 = Answer.builder()
            .author("jerome")
            .questionId(id2.toString())
            .content("content2")
            .build();
    List<Answer> list = new ArrayList<>();
    {
        list.add(newAnswer2);
    }

    @Test
    void testSaveRepository(){
        when(AR.save(newAnswer1)).thenReturn(1);
        try{
            assertEquals(1,AMF.saveAnswer(command));
        }catch (Exception e){ }
    }

    @Test
    void testSaveRepositoryFail(){
        when(AR.save(anyObject())).thenThrow(Exception.class);
        assertThrows(AnswerException.class, ()->AMF.saveAnswer(command2));
    }

    @Test
    void testgetRelatedAnswer(){
        when(AR.findAllforQuestion(id2)).thenReturn(list);
        assertEquals(1, AMF.getRelatedAnswer(id2).size());
    }
}
