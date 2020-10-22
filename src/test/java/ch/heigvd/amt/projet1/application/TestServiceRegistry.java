package ch.heigvd.amt.projet1.application;

import ch.heigvd.amt.projet1.application.answermanagement.AnswerManagementFacade;
import ch.heigvd.amt.projet1.application.commentmanagement.CommentCommand;
import ch.heigvd.amt.projet1.application.commentmanagement.CommentException;
import ch.heigvd.amt.projet1.application.commentmanagement.CommentManagementFacade;
import ch.heigvd.amt.projet1.application.identitymanagement.IdentityManagementFacade;
import ch.heigvd.amt.projet1.application.questionmanagement.QuestionManagementFacade;
import ch.heigvd.amt.projet1.application.statisticmanagement.StatisticManagementFacade;
import ch.heigvd.amt.projet1.domain.comment.Comment;
import ch.heigvd.amt.projet1.domain.comment.CommentId;
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
    public static WebArchive createSystemEndpointTestDeployment() {
        WebArchive archive = ShrinkWrap.create(WebArchive.class, WARNAME)
                .addPackages(true, "ch.heigvd.amt")
                .addPackages(true, "org.mindrot");
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
            //comment = CMF.saveComment(commentCommand);
            //result = CMF.getRelatedComment(comment.getId(), comment.getType());
            assertEquals(1, CMF.saveComment(commentCommand));
        }catch (CommentException e){}

        result = CMF.getRelatedComment(new CommentId(), "test");
        assertEquals(0, result.size());
    }

    @Test
    public void testAnswerFacade(){

    }

    @Test
    public void testStatisticFacade(){

    }
}
