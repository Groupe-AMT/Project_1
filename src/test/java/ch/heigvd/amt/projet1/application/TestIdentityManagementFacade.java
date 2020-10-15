package ch.heigvd.amt.projet1.application;
import org.junit.jupiter.api.Test;
import ch.heigvd.amt.projet1.application.identitymanagement.IdentityManagementFacade;
import ch.heigvd.amt.projet1.application.identitymanagement.login.RegisterCommand;
import static org.junit.jupiter.api.Assertions.*;

public class TestIdentityManagementFacade {
    IdentityManagementFacade id = new IdentityManagementFacade();
    RegisterCommand Rc = new RegisterCommand("jerome_A","jerome@heig-vd.ch","A", "jerome","password");

    @Test
    void Testregister(){
        //assertDoesNotThrow(());
    }

    @Test
    void Testauthenticate(){

    }
}
