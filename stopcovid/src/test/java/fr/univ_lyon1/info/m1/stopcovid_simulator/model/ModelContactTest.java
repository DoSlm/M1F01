package fr.univ_lyon1.info.m1.stopcovid_simulator.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelContactTest {

    private static ModelContact contact;

    @BeforeAll
    public static void setUp() {
        contact = new ModelContact("ID");
    }

    @Test
    public void getUserIdTest() {
        assertEquals("ID", contact.getUserId());
    }

    @Test
    public void getNumberMetTest() {
        assertEquals(1, contact.getNumberMet());
    }

    @Test
    public void incrementNumberMetTest() {
        int n = contact.getNumberMet();
        contact.incrementNumberMet();
        assertEquals(n+1, contact.getNumberMet());
    }
}