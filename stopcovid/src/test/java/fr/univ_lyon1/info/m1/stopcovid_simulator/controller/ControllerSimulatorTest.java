package fr.univ_lyon1.info.m1.stopcovid_simulator.controller;

import fr.univ_lyon1.info.m1.stopcovid_simulator.model.ModelContact;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.ModelStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ControllerSimulatorTest {

    private static ControllerSimulator controllerSimulatorTest;
    private static ArrayList<String> listTest = new ArrayList<>();

    @BeforeAll
    public static void setUp() {
        listTest.add("ID1");
        listTest.add("ID2");
        controllerSimulatorTest = new ControllerSimulator(listTest);
    }

    @Test
    public void getUserIdTest() {
        assertEquals(listTest.get(0), controllerSimulatorTest.getUserId(0));
        assertEquals(listTest.get(1), controllerSimulatorTest.getUserId(1));
    }

    @Test
    public void getUserContactsTest() {
    }

    @Test
    public void getUserStatusTest() {
        controllerSimulatorTest.setUserStatus(0, ModelStatus.NO_RISK);
        assertEquals(ModelStatus.NO_RISK, controllerSimulatorTest.getUserStatus(0));
    }

    @Test
    public void meetSimulatorTest() {
        controllerSimulatorTest.meetSimulator(listTest.get(0), listTest.get(1));
        assertEquals(listTest.get(1),
                controllerSimulatorTest.getUserContacts(0).get(0).getUserId());
        assertEquals(listTest.get(0),
                controllerSimulatorTest.getUserContacts(1).get(0).getUserId());
    }

    @Test
    public void declareInfectedTest() {
        controllerSimulatorTest.declareInfected(listTest.get(0));
        assertEquals(ModelStatus.INFECTED, controllerSimulatorTest.getUserStatus(0));
    }

    @Test
    public void getNbUsersTest() {
        assertEquals(2, controllerSimulatorTest.getNbUsers());
    }

    @Test
    public void setUserStatusTest() {
        controllerSimulatorTest.setUserStatus(0, ModelStatus.NO_RISK);
        assertEquals(ModelStatus.NO_RISK, controllerSimulatorTest.getUserStatus(0));
    }

    @Test
    public void getRiskyUsersTest() {

    }

    @Test
    public void setStrategySendAllTest() {
    }

    @Test
    public void setStrategySendTwiceMetTest() {
    }

    @Test
    public void removeContactTest() {
    }
}