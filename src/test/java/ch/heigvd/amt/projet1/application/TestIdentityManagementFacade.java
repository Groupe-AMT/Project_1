package ch.heigvd.amt.projet1.application;
import ch.heigvd.amt.projet1.application.identitymanagement.login.RegisterCommand;
import ch.heigvd.amt.projet1.application.identitymanagement.login.RegisterFailedException;
import ch.heigvd.amt.projet1.domain.person.IPersonRepository;
import ch.heigvd.amt.projet1.domain.person.Person;
import org.junit.jupiter.api.Test;
import ch.heigvd.amt.projet1.application.identitymanagement.IdentityManagementFacade;

import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestIdentityManagementFacade {
    IPersonRepository IPR = mock(IPersonRepository.class);
    IdentityManagementFacade id = new IdentityManagementFacade(IPR);
    RegisterCommand Rc = new RegisterCommand("jerome_A","jerome@heig-vd.ch","A", "jerome","password");
    Optional<Person> P = Optional.empty();

    @Test
    void Testregister() {
        when(IPR.findByUsername(anyObject())).thenReturn(P);
        when(IPR.save(anyObject())).thenReturn(1);
        assertThrows(RegisterFailedException.class, ()->id.register(Rc));
    }

    @Test
    void Testauthenticate(){

    }
}
