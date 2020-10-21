package ch.heigvd.amt.projet1.domain.person;
import org.mindrot.jbcrypt.BCrypt;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPerson {
    private final PersonId Pid = new PersonId();
    private final Person P = new Person( Pid,
            "QSaucy",
            "Quentin.saucy@heig-vd.ch",
            "Quentin",
            "Saucy",
            BCrypt.hashpw("Password", BCrypt.gensalt()));
    Person.PersonBuilder Pb = P.toBuilder();

    @Test
    void testAuthenticate (){
        assertFalse(P.authenticate("MauvaisMdp"));
        assertTrue(P.authenticate("Password"));
    }

    @Test
    void testPersonBuilderClearTextPassword (){
        assertThrows(IllegalArgumentException.class, () -> Pb.clearTextPassword(null));
        assertThrows(IllegalArgumentException.class, () -> Pb.clearTextPassword(""));
        assertNotNull(Pb.clearTextPassword("Password"));
        assertNotNull(Pb.clearTextPassword("MauvaisMdp"));
    }

    @Test
    void testPersonBuilderbuildUsername(){
        Pb.username(null);
        assertThrows(IllegalArgumentException.class, () -> Pb.build(), "Username is mandatory");
        Pb.username("");
        assertThrows(IllegalArgumentException.class, () -> Pb.build(), "Username is mandatory");
        Pb.username("salut");
        assertDoesNotThrow(() -> Pb.build(), "Username is mandatory");
    }

    @Test
    void testPersonBuilderbuildHashedPassword(){
        Pb.hashedPassword(null);
        assertThrows(IllegalArgumentException.class, () -> Pb.build(), "Password is mandatory");
        Pb.hashedPassword("");
        assertThrows(IllegalArgumentException.class, () -> Pb.build(), "Password is mandatory");
        Pb.hashedPassword("salut");
        assertDoesNotThrow(() -> Pb.build(), "Password is mandatory");
    }

    @Test
    void testPersonBuilderbuildEmail(){
        Pb.email(null);
        assertThrows(IllegalArgumentException.class, () -> Pb.build(), "Email is mandatory");
        Pb.email("");
        assertThrows(IllegalArgumentException.class, () -> Pb.build(), "Email is mandatory");
        Pb.email("salut");
        assertDoesNotThrow(() -> Pb.build(), "Email is mandatory");
    }

    @Test
    void testPersonBuilderbuildFirstname(){
        Pb.firstname(null);
        assertThrows(IllegalArgumentException.class, () -> Pb.build(), "Firstname is mandatory");
        Pb.firstname("");
        assertThrows(IllegalArgumentException.class, () -> Pb.build(), "Firstname is mandatory");
        Pb.firstname("salut");
        assertDoesNotThrow(() -> Pb.build(), "Firstname is mandatory");
    }

    @Test
    void testPersonBuilderbuildLastName(){
        Pb.lastName(null);
        assertThrows(IllegalArgumentException.class, () -> Pb.build(), "Lastname is mandatory");
        Pb.lastName("");
        assertThrows(IllegalArgumentException.class, () -> Pb.build(), "Lastname is mandatory");
        Pb.lastName("salut");
        assertDoesNotThrow(() -> Pb.build(), "Lastname is mandatory");
    }
}
