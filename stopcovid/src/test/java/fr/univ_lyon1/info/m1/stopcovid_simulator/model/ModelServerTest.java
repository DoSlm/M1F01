package fr.univ_lyon1.info.m1.stopcovid_simulator.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelServerTest {

    private static ModelServer testServer;

    @BeforeAll
    public static void setUp() {
        testServer = ModelServer.getInstance();
    }

    @Test
    void getInstanceTest() {
        final ModelServer testServer2 = ModelServer.getInstance();
        assertEquals(testServer, testServer2);
    }

    @Test
    void setStrategy() {
    }

    @Test
    void getStrategy() {
    }

    @Test
    void getRiskyUsers() {
    }

    @Test
    void notifyUsers() {
    }
}