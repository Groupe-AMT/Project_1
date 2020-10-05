package test.ch.heigvd.amt.projet1.domain.person;
import ch.heigvd.amt.projet1.domain.person.Person;
import ch.heigvd.amt.projet1.domain.person.PersonId;
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
    @Test
    void testAuthenticate (){
        assertFalse(P.authenticate("MauvaisMdp"));
        assertTrue(P.authenticate("Password"));
    }

    @Test
    void testPersonBuilder (){
        Person.PersonBuilder Pb = P.toBuilder();
        assertThrows(IllegalArgumentException.class, () -> Pb.clearTextPassword(null));
    }
}
