package ch.heigvd.amt.projet1.application;

import ch.heigvd.amt.projet1.application.answermanagement.AnswerCommand;
import ch.heigvd.amt.projet1.application.answermanagement.AnswerException;
import ch.heigvd.amt.projet1.application.answermanagement.AnswerManagementFacade;
import ch.heigvd.amt.projet1.application.commentmanagement.CommentCommand;
import ch.heigvd.amt.projet1.application.commentmanagement.CommentException;
import ch.heigvd.amt.projet1.application.commentmanagement.CommentManagementFacade;
import ch.heigvd.amt.projet1.application.identitymanagement.IdentityManagementFacade;
import ch.heigvd.amt.projet1.application.questionmanagement.QuestionManagementFacade;
import ch.heigvd.amt.projet1.application.statisticmanagement.StatisticManagementFacade;
import ch.heigvd.amt.projet1.domain.answer.Answer;
import ch.heigvd.amt.projet1.domain.comment.Comment;
import ch.heigvd.amt.projet1.domain.comment.CommentId;
import ch.heigvd.amt.projet1.domain.question.QuestionId;
import ch.heigvd.amt.projet1.domain.statistic.Statistic;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class TestServiceRegistry {

    private final static String WARNAME = "arquillian-managed.war";

    @Deployment(testable = true)
    public static WebArchive createDeployment() {
        WebArchive archive = ShrinkWrap.create(WebArchive.class, WARNAME)
                .addPackages(true, "ch.heigvd.amt");
        return archive;
    }

    @Inject
    ServiceRegistry SR;

    @Test
    public void testDependencyInjection(){
        IdentityManagementFacade IPR = SR.getIdentityFacade();
        QuestionManagementFacade QMF = SR.getQuestionFacade();
        AnswerManagementFacade AMF = SR.getAnswerFacade();
        StatisticManagementFacade SMF = SR.getStatisticFacade();
        CommentManagementFacade CMF = SR.getCommentFacade();
        assertNotNull(IPR);
        assertNotNull(QMF);
        assertNotNull(AMF);
        assertNotNull(SMF);
        assertNotNull(CMF);
    }

    @Test
    public void testCommentFacade(){
        CommentManagementFacade CMF = SR.getCommentFacade();
        CommentCommand commentCommand = CommentCommand.builder()
                .author("Jerome")
                .content("test arquillian")
                .type("Test")
                .Id("ID")
                .build();
        Comment comment ;
        List<Comment> result;
        try{
            assertEquals(1, CMF.saveComment(commentCommand));
        }catch (CommentException e){}

        result = CMF.getRelatedComment(new CommentId(), "test");
        assertEquals(0, result.size());
    }

    @Test
    public void testAnswerFacade(){
        QuestionId QID= new QuestionId();
        AnswerManagementFacade AMF = SR.getAnswerFacade();
        Answer newAnswer = Answer.builder()
                .author("jerome")
                .questionId(QID.toString())
                .content("content answer")
                .build();
        AnswerCommand answerCommand = AnswerCommand.builder()
                .author("jerome")
                .content("content answer")
                .questionId(QID.toString())
                .build();
        try {
            assertEquals(1,AMF.saveAnswer(answerCommand));
        }catch (AnswerException e){}
        List<Answer> result = AMF.getRelatedAnswer(QID);
        //assertEquals(1, result.size());
    }

    @Test
    public void testStatisticFacade(){
        StatisticManagementFacade SMF = SR.getStatisticFacade();
        Statistic stat = Statistic.builder()
                .nbQuestions(SR.questionRepository.Size())
                .nbAnswers(SR.answerRepository.Size())
                .nbComments(SR.commentRepository.Size())
                .nbUsers(SR.personRepository.Size())
                .nbSelfQuestions(SR.questionRepository.SizeFor("username"))
                .nbSelfAnswers(SR.answerRepository.SizeFor("username"))
                .nbSelfComments(SR.commentRepository.SizeFor("username"))
                .build();
        assertEquals(stat, SMF.getStats("username"));
    }
}
