package test.ch.heigvd.amt.projet1.domain;
import ch.heigvd.amt.projet1.domain.Authentification;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestAuthentification {
    private final Authentification aut = new Authentification();

    @Test
    void testIsExist(){
        assertTrue(aut.isExist("laure"));
        assertFalse(aut.isExist("Quentin"));
    }

    @Test
    void testIsSame(){

    }

    @Test
    void testSetAuth(){

    }
}

