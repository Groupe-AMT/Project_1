package ch.heigvd.amt.projet1.application.identitymanagement;

import ch.heigvd.amt.projet1.application.identitymanagement.authentificate.AuthentificateCommand;
import ch.heigvd.amt.projet1.application.identitymanagement.authentificate.AuthentificateFailedException;
import ch.heigvd.amt.projet1.application.identitymanagement.authentificate.CurrentUserDTO;
import ch.heigvd.amt.projet1.application.identitymanagement.login.RegisterCommand;
import ch.heigvd.amt.projet1.application.identitymanagement.login.RegisterFailedException;
import ch.heigvd.amt.projet1.application.identitymanagement.updatePassword.UpdatePasswordCommand;
import ch.heigvd.amt.projet1.application.identitymanagement.updatePassword.UpdatePasswordFailedException;
import ch.heigvd.amt.projet1.application.identitymanagement.updateProfile.UpdateProfileCommand;
import ch.heigvd.amt.projet1.application.identitymanagement.updateProfile.UpdateProfileFailedException;
import ch.heigvd.amt.projet1.domain.person.IPersonRepository;
import ch.heigvd.amt.projet1.domain.person.Person;
import ch.heigvd.amt.projet1.domain.person.PersonId;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.Mock;

import javax.faces.component.UpdateModelException;
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
    Person p = new Person(new PersonId(),"jerome_A",
            "jerome@heig-vd.ch",
            "jerome",
            "A",
            BCrypt.hashpw("Passw0rd", BCrypt.gensalt()));
    Person newPerson = Person.builder()
            .username("jerome_A")
            .firstname("jerome@heig-vd.ch")
            .lastName("A")
            .email("jerome@heig-vd.ch")
            .clearTextPassword("Passw0rd")
            .build();
    UpdatePasswordCommand updatePasswordCommand = UpdatePasswordCommand.builder()
            .prev_pass("Passw0rd")
            .new_pass("pass")
            .build();
    UpdatePasswordCommand UP = UpdatePasswordCommand.builder()
            .prev_pass("absdfk")
            .new_pass("Pahdkj68bdhdfjKJ")
            .build();
    UpdateProfileCommand UPC = UpdateProfileCommand.builder()
            .username("Qsaucy")
            .firstname("Quentin")
            .lastname("Saucy")
            .email("Q.S@H.ch")
            .build();

    @Test
    void testregisterUserDontExist() {
        Optional<Person> P = Optional.of(p);
        when(IPR.findByUsername(anyString())).thenReturn(P);
        when(IPR.save(newPerson)).thenReturn(1);
        assertThrows(RegisterFailedException.class, ()->id.register(Rc));
    }

    @Test
    void testregisterUserAlreadyExist() {
        Optional<Person> P = Optional.empty();
        when(IPR.findByUsername(anyString())).thenReturn(P);
        when(IPR.save(newPerson)).thenReturn(1);
        assertDoesNotThrow(()->id.register(Rc));
    }

    @Test
    void testauthenticateFailed(){
        Optional<Person> P = Optional.empty();
        when(IPR.findByUsername(anyString())).thenReturn(P);
        assertThrows(AuthentificateFailedException.class, ()->id.authenticate(Ac));
    }

    @Test
    void testauthenticateOk(){
        Optional<Person> P = Optional.of(p);
        when(IPR.findByUsername(anyString())).thenReturn(P);
        assertDoesNotThrow(()->id.authenticate(Ac));
        try{
            assertEquals(id.authenticate(Ac), CDTO);
        }catch (AuthentificateFailedException e){ }
    }

    @Test
    void testUpdatePasswordFailed(){
        assertThrows(UpdatePasswordFailedException.class, ()->id.updatePassword(CDTO,updatePasswordCommand));
        Optional<Person> P = Optional.empty();
        when(IPR.findByUsername(anyString())).thenReturn(P);
        assertThrows(UpdatePasswordFailedException.class, ()->id.updatePassword(CDTO,UP));
    }

    @Test
    void testUpdatePasswordFailed2(){
        Optional<Person> P = Optional.of(p);
        when(IPR.findByUsername(anyString())).thenReturn(P);
        assertThrows(UpdatePasswordFailedException.class, ()->id.updatePassword(CDTO,UP));
    }

    @Test
    void testUpdateProfilFailed(){
        Optional<Person> P = Optional.of(p);
        when(IPR.findByUsername(anyString())).thenReturn(P);
        assertThrows(UpdateProfileFailedException.class, ()->id.updateProfile(CDTO,UPC));
    }

    @Test
    void testUpdateProfilFailed1() throws UpdateProfileFailedException {
        UpdateProfileCommand UPC1 = UpdateProfileCommand.builder()
                .username("jerome_A")
                .firstname("")
                .lastname("")
                .email("")
                .build();
        UpdateProfileCommand UPC2 = UpdateProfileCommand.builder()
                .username("jerome_A")
                .firstname("jerome")
                .lastname("")
                .email("")
                .build();
        UpdateProfileCommand UPC3 = UpdateProfileCommand.builder()
                .username("jerome_A")
                .firstname("")
                .lastname("A")
                .email("")
                .build();
        UpdateProfileCommand UPC4 = UpdateProfileCommand.builder()
                .username("jerome_A")
                .firstname("")
                .lastname("")
                .email("jerome@heig-vd.ch")
                .build();
        Optional<Person> P = Optional.of(p);
        when(IPR.findByUsername(anyString())).thenReturn(P);

        CurrentUserDTO C1 = id.updateProfile(CDTO,UPC1);
        assertEquals(CDTO.getUsername(), C1.getUsername());
        CurrentUserDTO C2 = id.updateProfile(CDTO,UPC2);
        assertEquals(CDTO.getFirstname(), C2.getFirstname());
        CurrentUserDTO C3 = id.updateProfile(CDTO,UPC3);
        assertEquals(CDTO.getLastname(), C3.getLastname());
        CurrentUserDTO C4 = id.updateProfile(CDTO,UPC4);
        assertEquals(CDTO.getEmail(), C4.getEmail());
    }
}
