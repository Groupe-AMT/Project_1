package ch.heigvd.amt.projet1.application.statisticmanagement;

import java.util.ArrayList;

import ch.heigvd.amt.projet1.domain.answer.IAnswerRepository;
import ch.heigvd.amt.projet1.domain.comment.ICommentRepository;
import ch.heigvd.amt.projet1.domain.person.IPersonRepository;
import ch.heigvd.amt.projet1.domain.question.IQuestionRepository;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestStatisticManagement {
    IQuestionRepository IQR = mock(IQuestionRepository.class);
    IAnswerRepository IAR = mock(IAnswerRepository.class);
    ICommentRepository ICR = mock(ICommentRepository.class);
    IPersonRepository IPR = mock(IPersonRepository.class);

    @Test
    void testGetStats(){

    }

    @Test
    void testQuestionSize(){

    }
}
