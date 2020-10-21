package ch.heigvd.amt.projet1.application;

import ch.heigvd.amt.projet1.application.identitymanagement.IdentityManagementFacade;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.runner.RunWith;
import static org.junit.jupiter.api.Assertions.*;

import javax.inject.Inject;

@RunWith(Arquillian.class)
public class TestServiceRegistry {

    private final static String WARNAME = "arquillian-managed.war";

    @Inject
    ServiceRegistry SR;

    @Deployment(testable = true)
    public static WebArchive createSystemEndpointTestDeployment() {
        WebArchive archive = ShrinkWrap.create(WebArchive.class, WARNAME)
                .addPackages(true, "ch.heigvd.amt");
        return archive;
    }

    public void test (){
        IdentityManagementFacade IPR = SR.getIdentityFacade();
        assertEquals("2","1");
    }
}
