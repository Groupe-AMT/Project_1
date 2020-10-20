package ch.heigvd.amt.projet1.application;
import ch.heigvd.amt.projet1.application.identitymanagement.authentificate.AuthentificateCommand;
import ch.heigvd.amt.projet1.application.identitymanagement.authentificate.AuthentificateFailedException;
import ch.heigvd.amt.projet1.application.identitymanagement.authentificate.CurrentUserDTO;
import ch.heigvd.amt.projet1.application.identitymanagement.login.RegisterCommand;
import ch.heigvd.amt.projet1.application.identitymanagement.login.RegisterFailedException;
import ch.heigvd.amt.projet1.domain.person.IPersonRepository;
import ch.heigvd.amt.projet1.domain.person.Person;
import ch.heigvd.amt.projet1.domain.person.PersonId;
import org.junit.jupiter.api.Test;
import ch.heigvd.amt.projet1.application.identitymanagement.IdentityManagementFacade;

import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestIdentityManagementFacade {
    IPersonRepository IPR = mock(IPersonRepository.class);
    IdentityManagementFacade id = new IdentityManagementFacade(IPR);
    RegisterCommand Rc = RegisterCommand.builder()
            .username("jerome_A")
            .firstname("jerome@heig-vd.ch")
            .lastname("A")
            .email("jerome@heig-vd.ch")
            .clearPassword("password")
            .build();
    AuthentificateCommand Ac = AuthentificateCommand.builder()
            .username("jerome_A")
            .clearPassword("password")
            .build();
    CurrentUserDTO CDTO = CurrentUserDTO.builder()
            .username("jerome_A")
            .firstname("jerome")
            .lastname("A")
            .email("jerome@heig-vd.ch")
            .build();
    Person p = new Person(new PersonId(),"jerome_A","jerome@heig-vd.ch","jerome", "A","password");

    @Test
    void TestregisterUserDontExist() {
        Optional<Person> P = Optional.of(p);
        when(IPR.findByUsername(anyObject())).thenReturn(P);
        when(IPR.save(anyObject())).thenReturn(1);
        assertThrows(RegisterFailedException.class, ()->id.register(Rc));
    }

    @Test
    void TestregisterUserAlreadyExist() {
        Optional<Person> P = Optional.empty();
        when(IPR.findByUsername(anyObject())).thenReturn(P);
        when(IPR.save(anyObject())).thenReturn(1);
        assertDoesNotThrow(()->id.register(Rc));
    }

    @Test
    void TestauthenticateFailed(){
        Optional<Person> P = Optional.empty();
        when(IPR.findByUsername(anyObject())).thenReturn(P);
        assertThrows(AuthentificateFailedException.class, ()->id.authenticate(Ac));
    }

    @Test
    void TestauthenticateOk(){
        Optional<Person> P = Optional.of(p);
        when(IPR.findByUsername(anyObject())).thenReturn(P);
        assertDoesNotThrow(()->id.authenticate(Ac));
        try{
            assertEquals(id.authenticate(Ac), CDTO);
        }catch (AuthentificateFailedException e){ }
    }
}
