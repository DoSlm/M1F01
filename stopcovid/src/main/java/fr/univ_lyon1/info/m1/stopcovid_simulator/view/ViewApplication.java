package fr.univ_lyon1.info.m1.stopcovid_simulator.view;

import fr.univ_lyon1.info.m1.stopcovid_simulator.controller.ControllerSimulator;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ViewApplication extends VBox {
    private int numberUsers;
    private ArrayList<ViewUser> viewUsers = new ArrayList<>();
    private ViewServer viewServer;
    private ControllerSimulator controllerSimulator;
    private ArrayList<String> listUsers = new ArrayList<>();

    /** View for the whole application.
     * @param stage The JavaFX stage where everything will be displayed.
     * @param width width in px
     * @param height height in px
     * @param nbUsers number of users to manage
     */
    public ViewApplication(final Stage stage, final int width,
                           final int height, final int nbUsers) {
        numberUsers = nbUsers;

        // Name of window
        stage.setTitle("StopCovid Simulator");


        // Build the list of users
        for (int i = 1; i <= nbUsers; i++) {
            listUsers.add("user".concat(String.valueOf(i)));
        }


        // Creation of the instances
        controllerSimulator = new ControllerSimulator(listUsers);

        viewServer = new ViewServer(controllerSimulator);

        for (int i = 0; i < nbUsers; i++) {
            ViewUser v = new ViewUser(listUsers, this, controllerSimulator, i);
            viewUsers.add(v);
        }


        // Interface
        final VBox root = this;

        final HBox usersBox = new HBox();
        for (int i = 0; i < nbUsers; i++) {
            usersBox.getChildren().addAll(viewUsers.get(i).getGui());
        }

        final VBox serverBox = new VBox();
        serverBox.getChildren().add(viewServer.getGui());

        root.setAlignment(Pos.TOP_CENTER);
        root.getChildren().addAll(new Label("User Interfaces"), new Separator(),
                usersBox, new Separator(), new Label(""),
                new Label("Server Interface"), new Separator(),
                new Label(""), serverBox);

        final Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Updates all instances of ViewUser.
     */
    public void updateAllViews() {
        for (int i = 0; i < numberUsers; i++) {
            viewUsers.get(i).updateViewUser();
        }
        viewServer.update();
    }
}
