package ch.heigvd.amt.projet1.application;

import ch.heigvd.amt.projet1.application.identitymanagement.IdentityManagementFacade;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import javax.inject.Inject;
import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class TestServiceRegistry {

    private final static String WARNAME = "arquillian-managed.war";

    @Deployment(testable = true)
    public static WebArchive createSystemEndpointTestDeployment() {
        WebArchive archive = ShrinkWrap.create(WebArchive.class, WARNAME)
                .addPackages(true, "ch.heigvd.amt.projet1")
                .addPackages(true, "org.mindrot");
        return archive;
    }

    @Inject
    ServiceRegistry SR;

    @Test
    public void test (){
        //IdentityManagementFacade IPR = SR.getIdentityFacade();
        assertEquals("2","1");
    }
}
