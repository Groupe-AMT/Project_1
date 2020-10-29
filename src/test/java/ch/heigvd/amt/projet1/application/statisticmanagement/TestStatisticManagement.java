package ch.heigvd.amt.projet1.application.statisticmanagement;

import java.util.ArrayList;

import ch.heigvd.amt.projet1.domain.answer.IAnswerRepository;
import ch.heigvd.amt.projet1.domain.comment.ICommentRepository;
import ch.heigvd.amt.projet1.domain.person.IPersonRepository;
import ch.heigvd.amt.projet1.domain.question.IQuestionRepository;
import ch.heigvd.amt.projet1.domain.statistic.Statistic;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestStatisticManagement {
    IQuestionRepository IQR = mock(IQuestionRepository.class);
    IAnswerRepository IAR = mock(IAnswerRepository.class);
    ICommentRepository ICR = mock(ICommentRepository.class);
    IPersonRepository IPR = mock(IPersonRepository.class);
    StatisticManagementFacade STMF = new StatisticManagementFacade(IQR,IAR,ICR,IPR);

    @Test
    void testGetStats(){
        when(IQR.Size()).thenReturn(1);
        when(IAR.Size()).thenReturn(2);
        when(ICR.Size()).thenReturn(3);
        when(IPR.Size()).thenReturn(4);
        when(IQR.SizeFor(anyString())).thenReturn(5);
        when(IAR.SizeFor(anyString())).thenReturn(6);
        when(ICR.SizeFor(anyString())).thenReturn(7);

        Statistic stat = Statistic.builder()
                .nbQuestions(1)
                .nbAnswers(2)
                .nbComments(3)
                .nbUsers(4)
                .nbSelfQuestions(5)
                .nbSelfAnswers(6)
                .nbSelfComments(7)
                .build();

        assertEquals(stat, STMF.getStats("username"));
    }

    @Test
    void testQuestionSize(){
        when(IQR.Size()).thenReturn(20);
        assertEquals(20, STMF.getQuestionSize());
    }
}
