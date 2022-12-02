package fr.univ_lyon1.info.m1.stopcovid_simulator.model;

public class ModelContact {
    private final String userId;
    private int numberMet;

    /**
     * Constructor.
     * @param id the id of the contact
     */
    public ModelContact(final String id) {
        userId = id;
        numberMet = 1;
    }

    /**
     * Increments the the number of time this contact was met.
     */
    public void incrementNumberMet() {
        numberMet += 1;
    }

    /**
     * Returns the id of the contact.
     * @return the id of the contact
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Returns the number of time this contact was met.
     * @return the number of time this contact was met
     */
    public int getNumberMet() {
        return numberMet;
    }
}
