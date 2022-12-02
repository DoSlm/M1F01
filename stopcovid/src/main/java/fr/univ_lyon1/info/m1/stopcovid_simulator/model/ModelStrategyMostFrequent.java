package fr.univ_lyon1.info.m1.stopcovid_simulator.model;

/**
 * Strategy to send the ten most common contacts.
 */
public class ModelStrategyMostFrequent implements  ModelStrategy {

    @Override
    public boolean execute(final ModelContact contact) {
        return true;
    }
}
