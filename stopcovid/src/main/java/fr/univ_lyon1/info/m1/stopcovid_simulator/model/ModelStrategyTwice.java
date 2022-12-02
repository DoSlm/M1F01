package fr.univ_lyon1.info.m1.stopcovid_simulator.model;

/**
 * Strategy to send contacts met twice.
 */
public class ModelStrategyTwice  implements ModelStrategy {

    @Override
    public boolean execute(final ModelContact contact) {
        return (contact.getNumberMet() > 1);
    }
}
