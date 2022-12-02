package fr.univ_lyon1.info.m1.stopcovid_simulator.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ModelUserTest {

    private static ModelUser modelUser;

    @BeforeAll
    public static void setUp() {
        modelUser = new ModelUser("ID");
    }

    @Test
    void setUserStatusTest() {
        modelUser.setUserStatus(ModelStatus.RISKY);
        assertEquals(ModelStatus.RISKY, modelUser.getUserStatus());
    }

    @Test
    void getUserStatusTest() {
        modelUser.setUserStatus(ModelStatus.RISKY);
        assertEquals(ModelStatus.RISKY, modelUser.getUserStatus());
    }

    @Test
    void addContactTest() {
        modelUser.addContact("test");
        assertNotEquals(0, modelUser.getContactList().size());
        modelUser.deleteContact("test");
    }

    @Test
    void getContactIdTest() {
        modelUser.addContact("test");
        assertEquals("test", modelUser.getContactId(0));
        modelUser.deleteContact("test");
    }

    @Test
    void getContactNumberMetTest() {
        modelUser.addContact("test");
        assertEquals(1, modelUser.getContactNumberMet(0));
        modelUser.addContact("test");
        assertEquals(2, modelUser.getContactNumberMet(0));
        modelUser.deleteContact("test");
    }

    @Test
    void getContactListTest() {
        ArrayList<ModelContact> testList = new ArrayList<>();
        testList.add(new ModelContact("test"));
        modelUser.addContact("test");
        assertEquals(testList.get(0).getUserId(),
                modelUser.getContactList().get(0).getUserId());

        assertEquals(testList.get(0).getNumberMet(),
                modelUser.getContactList().get(0).getNumberMet());
        modelUser.deleteContact("test");
    }

    @Test
    void getUserIdTest() {
        assertEquals("ID", modelUser.getUserId());
    }

    @Test
    void deleteContactTest() {
        int n = modelUser.getContactList().size();
        modelUser.addContact("test_delete");
        modelUser.deleteContact("test_delete");
        assertEquals(n, modelUser.getContactList().size());
    }
}