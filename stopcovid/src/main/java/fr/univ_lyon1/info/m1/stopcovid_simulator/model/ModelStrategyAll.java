package fr.univ_lyon1.info.m1.stopcovid_simulator.model;

/**
 * Strategy to send all contacts (regardless of the number of times met).
 */
public class ModelStrategyAll implements  ModelStrategy {

    @Override
    public boolean execute(final ModelContact contact) {
        return true;
    }
}
