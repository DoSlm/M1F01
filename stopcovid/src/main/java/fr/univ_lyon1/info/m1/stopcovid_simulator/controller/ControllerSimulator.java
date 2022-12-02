package fr.univ_lyon1.info.m1.stopcovid_simulator.controller;

import fr.univ_lyon1.info.m1.stopcovid_simulator.model.ModelUser;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.ModelStatus;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.ModelServer;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.ModelContact;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.ModelStrategy;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.ModelStrategyAll;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.ModelStrategyTwice;


import java.util.ArrayList;

public class ControllerSimulator {

    private int nbUsers = 0;
    private ModelServer server;
    private ArrayList<ModelUser> listUsers = new ArrayList<ModelUser>();
    private final int numberUsers;

    /**
     * Constructor.
     * @param users list of userId
     */
    public ControllerSimulator(final ArrayList<String> users) {
        nbUsers = users.size();
        server = ModelServer.getInstance();
        numberUsers = users.size();
        for (int i = 0; i < numberUsers; i++) {
            listUsers.add(new ModelUser(users.get(i)));
        }
    }

    /**
     * Returns the id of the user of index i.
     * @param i index of the user in listUser
     * @return the id of the user of index i
     */
    public String getUserId(final int i) {
        return listUsers.get(i).getUserId();
    }

    /**
     * Returns all the contacts of the user of index i.
     * @param i index of the user in listUser
     * @return all the contacts of the user of index i
     */
    public ArrayList<ModelContact> getUserContacts(final int i) {
        return listUsers.get(i).getContactList();
    }

    /**
     * Returns the status of of the user of index i.
     * @param i index of the user in listUser
     * @return the status of of the user of index i
     */
    public ModelStatus getUserStatus(final int i) {
        return listUsers.get(i).getUserStatus();
    }

    /**
     *  Adds contact to each user involve in a meeting.
     * @param user1
     * @param user2
     */
    public void meetSimulator(final String user1, final String user2) {
        for (int i = 0; i < numberUsers; i++) {
            if (user1.equals(listUsers.get(i).getUserId())) {
                listUsers.get(i).addContact(user2);
            } else if (user2.equals(listUsers.get(i).getUserId())) {
                listUsers.get(i).addContact(user1);
            }
        }
    }

    /**
     * Declare a user INFECTED and set contacts to RISKY.
     * @param user
     */
    public void declareInfected(final String user) {
        int id = -1;
        for (int i = 0; i < numberUsers; i++) {
            if (listUsers.get(i).getUserId().equals(user)) {
                id = i;
            }
        }

        listUsers.get(id).setUserStatus(ModelStatus.INFECTED);
        if (!listUsers.get(id).userToNotify(server.getStrategy()).isEmpty()) {
            server.notifyUsers(this, listUsers.get(id).userToNotify(server.getStrategy()));
        }
    }

    /**
     * Return the number of users.
     * @return nbUsers
     */
    public int getNbUsers() {
        return nbUsers;
    }

    /**
     * Sets the status a usr on index i.
     * @param i the index of the user
     * @param status the new status
     */
    public void setUserStatus(final int i, final ModelStatus status) {
        listUsers.get(i).setUserStatus(status);
    }

    /**
     * Return list of risky users.
     * @return list of risky users
     */
    public ArrayList<String> getRiskyUsers() {
        return server.getRiskyUsers();
    }

    /**
     * Sets new strategy to set users as RISKY to send all contacts.
     */
    public void setStrategySendAll() {
        ModelStrategy s = new ModelStrategyAll();
        server.setStrategy(s);
    }

    /**
     * Sets new strategy to set users as RISKY to send all contacts.
     */
    public void setStrategySendTwiceMet() {
        ModelStrategy s = new ModelStrategyTwice();
        server.setStrategy(s);
    }

    /**
     * Removes the contact with the name passed in parameter.
     * @param userIndex the user wanting to remove the contact
     * @param contactId the name of the contact
     */
    public void removeContact(final int userIndex, final String contactId) {
        listUsers.get(userIndex).deleteContact(contactId);
    }
}
