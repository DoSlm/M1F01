package fr.univ_lyon1.info.m1.stopcovid_simulator.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelStrategyAllTest {

    @Test
    public void executeTest() {
        final ModelContact contact = new ModelContact("test");
        ModelStrategy strategyTest = new ModelStrategyAll();
        assertEquals(true,strategyTest.execute(contact));
    }
}