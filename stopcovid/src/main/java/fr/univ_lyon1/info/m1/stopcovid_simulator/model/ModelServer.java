package fr.univ_lyon1.info.m1.stopcovid_simulator.model;

import fr.univ_lyon1.info.m1.stopcovid_simulator.controller.ControllerSimulator;

import java.util.ArrayList;

public final class ModelServer {
    private ModelStrategy strategy;
    private ArrayList<String> riskyUsers = new ArrayList<>();

    /**
     * Constructor.
     */
    private ModelServer() {
        this.strategy = new ModelStrategyTwice();
    }

    private static ModelServer instance = new ModelServer();

    public static ModelServer getInstance() {
        return instance;
    }

    /**
     * Modifies the current strategy.
     * @param newStrategy
     */
    public void setStrategy(final ModelStrategy newStrategy) {
        this.strategy = newStrategy;
    }

    /**
     * Returns the current strategy.
     * @return strategy
     */
    public ModelStrategy getStrategy() {
        return strategy;
    }


    /**
     * Returns list of risky users.
     * @return list of risky users
     */
    public ArrayList<String> getRiskyUsers() {
        return riskyUsers;
    }

    /**
     * Updates status of user to RISKY.
     * @param toContact the users to consider
     * @param controllerSim the controller to update
     */
    public void notifyUsers(final ControllerSimulator controllerSim,
                            final ArrayList<String> toContact) {
        if (!toContact.isEmpty()) {
            for (int i = 0; i < toContact.size(); i++) {
                for (int j = 0; j < controllerSim.getNbUsers(); j++) {
                    if (toContact.get(i).equals(controllerSim.getUserId(j))) {
                        this.riskyUsers.add(toContact.get(i));
                        if (controllerSim.getUserStatus(j) != ModelStatus.INFECTED) {
                            controllerSim.setUserStatus(j, ModelStatus.RISKY);
                        }
                    }
                }
            }
        }
    }
}
