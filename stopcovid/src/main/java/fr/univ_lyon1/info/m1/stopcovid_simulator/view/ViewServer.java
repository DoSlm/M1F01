package fr.univ_lyon1.info.m1.stopcovid_simulator.view;

import fr.univ_lyon1.info.m1.stopcovid_simulator.controller.ControllerSimulator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;


public class ViewServer {
    private final ControllerSimulator controllerSimulator;
    private final VBox gui = new VBox();
    private VBox currentStrategy = new VBox();
    private VBox listRiskyUsers = new VBox();
    private final EventHandler<ActionEvent> setStrategySendAll = new EventHandler<ActionEvent>() {
        @Override
        public void handle(final ActionEvent event) {
            controllerSimulator.setStrategySendAll();
            currentStrategy.getChildren().clear();
            currentStrategy.getChildren().add(new Label("Current strategy : Send all contacts"));
        }
    };
    private final EventHandler<ActionEvent> setStrategyTwice = new EventHandler<ActionEvent>() {
        @Override
        public void handle(final ActionEvent event) {
            controllerSimulator.setStrategySendTwiceMet();
            currentStrategy.getChildren().clear();
            currentStrategy.getChildren().add(
                    new Label("Current strategy : Send contacts met at least twice"));
        }
    };



    /**
     * Constructor.
     * @param controllerSim the controller of the simulation
     */
    public ViewServer(final ControllerSimulator controllerSim) {
        controllerSimulator = controllerSim;

        final Button btnAll = new Button("Send all contacts");
        btnAll.setOnAction(setStrategySendAll);

        final Button btnTwice = new Button("Send contacts met at least twice");
        controllerSimulator.setStrategySendAll();
        btnTwice.setOnAction(setStrategyTwice);
        currentStrategy.getChildren().add(new Label("Current strategy : Send all contacts"));

        final HBox buttonsBox = new HBox();
        buttonsBox.getChildren().addAll(btnAll, new Label("   "), btnTwice);

        update();

        listRiskyUsers.setAlignment(Pos.BASELINE_CENTER);
        currentStrategy.setAlignment(Pos.BASELINE_CENTER);
        buttonsBox.setAlignment(Pos.BASELINE_CENTER);

        gui.setAlignment(Pos.BASELINE_CENTER);
        gui.getChildren().addAll(currentStrategy, new Label(""), buttonsBox,
                new Label(" "), new Separator(), new Label(" "),
                new Label("Risky users : "), listRiskyUsers);
    }

    /**
     * Get the GUI object corresponding to the server.
     */
    public Node getGui() {
        return gui;
    }

    /**
    * Updates the list of risky users.
    */
    public void update() {
        listRiskyUsers.getChildren().clear();
        ArrayList<String> risky = controllerSimulator.getRiskyUsers();

        if (risky.isEmpty()) {
            listRiskyUsers.getChildren().add(new Label("None"));
        }

        for (int i = 0; i < risky.size(); i++) {
            listRiskyUsers.getChildren().add(new Label(risky.get(i)));
        }
    }
}
