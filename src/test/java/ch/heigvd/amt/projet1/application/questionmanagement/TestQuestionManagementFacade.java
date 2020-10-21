package ch.heigvd.amt.projet1.application.questionmanagement;

import ch.heigvd.amt.projet1.domain.question.IQuestionRepository;
import ch.heigvd.amt.projet1.domain.question.Question;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestQuestionManagementFacade {
    IQuestionRepository IQR = mock(IQuestionRepository.class);
    QuestionManagementFacade QMF = new QuestionManagementFacade(IQR);
    List<String> list = new ArrayList<>();
    {
        list.add("TAG");
        list.add("TAG 2");
    }

    Question Q1 = Question.builder()
            .author("Jerome")
            .Subject("test")
            .Tags(list)
            .content("Contenu de la question")
            .build();
    Question Q2 = Question.builder()
            .author("Jerome")
            .Subject("test1")
            .Tags(list)
            .content("Contenu de la question2")
            .build();
    List<Question> listQuestion = new ArrayList<>();
    {
        listQuestion.add(Q1);
        listQuestion.add(Q2);
    }

    QuestionCommand Qc =QuestionCommand.builder()
            .author("Jerome")
            .content("Contenu de la question")
            .subject("test")
            .tags(list)
            .build();

    @Test
    void testSaveQuestionOK(){
        when(IQR.save(anyObject())).thenReturn(1);
        assertDoesNotThrow(()->QMF.saveQuestion(Qc));
    }

    @Test
    void testSaveQuestionFailed(){
        doThrow(Exception.class).when(IQR).save(anyObject());
        assertThrows(QuestionException.class, ()->QMF.saveQuestion(Qc));
    }

    @Test
    void testListQuestion(){
        when(IQR.findAll()).thenReturn(listQuestion);
        assertEquals(listQuestion, QMF.getQuestions());
    }
}
