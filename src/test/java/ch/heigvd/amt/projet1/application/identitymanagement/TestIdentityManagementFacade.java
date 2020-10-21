package ch.heigvd.amt.projet1.application.identitymanagement;

import ch.heigvd.amt.projet1.application.identitymanagement.authentificate.AuthentificateCommand;
import ch.heigvd.amt.projet1.application.identitymanagement.authentificate.AuthentificateFailedException;
import ch.heigvd.amt.projet1.application.identitymanagement.authentificate.CurrentUserDTO;
import ch.heigvd.amt.projet1.application.identitymanagement.login.RegisterCommand;
import ch.heigvd.amt.projet1.application.identitymanagement.login.RegisterFailedException;
import ch.heigvd.amt.projet1.domain.person.IPersonRepository;
import ch.heigvd.amt.projet1.domain.person.Person;
import ch.heigvd.amt.projet1.domain.person.PersonId;
import org.junit.jupiter.api.Test;
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
            .clearPassword("Passw0rd")
            .build();
    AuthentificateCommand Ac = AuthentificateCommand.builder()
            .username("jerome_A")
            .clearPassword("Passw0rd")
            .build();
    CurrentUserDTO CDTO = CurrentUserDTO.builder()
            .username("jerome_A")
            .firstname("jerome")
            .lastname("A")
            .email("jerome@heig-vd.ch")
            .build();
    Person p = new Person(new PersonId(),"jerome_A","jerome@heig-vd.ch","jerome", "A","password");
    Person newPerson = Person.builder()
            .username("jerome_A")
            .firstname("jerome@heig-vd.ch")
            .lastName("A")
            .email("jerome@heig-vd.ch")
            .clearTextPassword("Passw0rd")
            .build();

    @Test
    void TestregisterUserDontExist() {
        Optional<Person> P = Optional.of(p);
        when(IPR.findByUsername(anyString())).thenReturn(P);
        when(IPR.save(newPerson)).thenReturn(1);
        assertThrows(RegisterFailedException.class, ()->id.register(Rc));
    }

    @Test
    void TestregisterUserAlreadyExist() {
        Optional<Person> P = Optional.empty();
        when(IPR.findByUsername(anyString())).thenReturn(P);
        when(IPR.save(newPerson)).thenReturn(1);
        assertDoesNotThrow(()->id.register(Rc));
    }

    @Test
    void TestauthenticateFailed(){
        Optional<Person> P = Optional.empty();
        when(IPR.findByUsername(anyString())).thenReturn(P);
        assertThrows(AuthentificateFailedException.class, ()->id.authenticate(Ac));
    }

    @Test
    void TestauthenticateOk(){
        Optional<Person> P = Optional.of(p);
        when(IPR.findByUsername(anyString())).thenReturn(P);
        assertDoesNotThrow(()->id.authenticate(Ac));
        try{
            assertEquals(id.authenticate(Ac), CDTO);
        }catch (AuthentificateFailedException e){ }
    }
}
