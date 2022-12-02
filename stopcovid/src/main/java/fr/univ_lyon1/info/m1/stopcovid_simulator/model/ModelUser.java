package fr.univ_lyon1.info.m1.stopcovid_simulator.model;

import java.util.ArrayList;

public class ModelUser {
    private String userId = new String();
    private ModelStatus userStatus = ModelStatus.NO_RISK;
    private ArrayList<ModelContact> contactList = new ArrayList<>();

    /**
     * Constructor.
     * @param id the id of the user
     */
    public ModelUser(final String id) {
        this.userId = id;
        userStatus = ModelStatus.NO_RISK;
        contactList = new ArrayList<ModelContact>();
    }

    /**
     * Returns the list of users to notify.
     * @param strategy  the current strategy of notifying users
     * @return list of users to notify
     */
    public ArrayList<String> userToNotify(final ModelStrategy strategy) {
        ArrayList<String> toNotify = new ArrayList<String>();
        if (!contactList.isEmpty()) {
            for (int i = 0; i < contactList.size(); i++) {
                if (strategy.execute(contactList.get(i))) {
                    toNotify.add(contactList.get(i).getUserId());
                }
            }
        }
        return toNotify;
    }


    /**
     * Sets the status as RISKY.
     * @param status The new status
     */
    public void setUserStatus(final ModelStatus status) {
        this.userStatus = status;
    }

    /**
     *  Returns the id of a contact.
     * @param pos the position of the contact in the list listContacts.
     * @return id of contact
     */
    public String getContactId(final int pos) {
        return contactList.get(pos).getUserId();
    }

    /**
     * Returns the number of times a contact was met.
     * @param pos the position of the contact in the list listContacts
     * @return the number of times a contact was met
     */
    public int getContactNumberMet(final int pos) {
        return contactList.get(pos).getNumberMet();
    }

    /**
     * Returns the list of contact od this user.
     * @return the list of contact od this user
     */
    public ArrayList<ModelContact> getContactList() {
        return contactList;
    }

    /**
     * Returns the id of this user.
     * @return the id of this user
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Return the status of this user.
     * @return the status of this user
     */
    public ModelStatus getUserStatus() {
        return userStatus;
    }

    /**
     * Adds a contact to the list if not already listed
     * or increments the number of meetings if it is.
     * @param other the contact to add
     */
    public void addContact(final String other) {
        boolean toList = true;
        for (int i = 0; i < contactList.size(); i++) {
            if (other.equals(contactList.get(i).getUserId())) {
                contactList.get(i).incrementNumberMet();
                toList = false;
            }
        }
        if (toList) {
            contactList.add(new ModelContact(other));
        }
    }

    /**
     * Removes a contact from the list.
     * @param id name of the contact
     */
    public void deleteContact(final String id) {
        for (int i = 0; i < contactList.size(); i++) {
            if (contactList.get(i).getUserId().equals(id)) {
                contactList.remove(i);
            }
        }
    }
}


