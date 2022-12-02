package fr.univ_lyon1.info.m1.stopcovid_simulator.model;

/**
 * Strategy interface.
 */
@FunctionalInterface
public interface ModelStrategy {
    /**
     * Return true if the contact should be notified.
     * @param contact
     * @returntrue if the contact should be notified
     */
    boolean execute(ModelContact contact);
}
