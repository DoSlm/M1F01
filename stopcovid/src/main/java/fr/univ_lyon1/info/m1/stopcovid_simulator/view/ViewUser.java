package fr.univ_lyon1.info.m1.stopcovid_simulator.view;

import fr.univ_lyon1.info.m1.stopcovid_simulator.controller.ControllerSimulator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;

import java.util.ArrayList;


public class ViewUser {
    private final ViewApplication viewToUpdate;
    private final ControllerSimulator controllerSimulator;
    private final String name;
    private final VBox gui = new VBox();
    private final VBox contacts = new VBox();
    private final int userIndex;
    private final ComboBox<String> otherUsers = new ComboBox<>();
    private Label status;

    private final EventHandler<ActionEvent> declare = new EventHandler<ActionEvent>() {
        @Override
        public void handle(final ActionEvent event) {
            declareMeInfected();
            updateViewUser();
        }
    };

    private final EventHandler<ActionEvent> meet = new EventHandler<ActionEvent>() {
        @Override
        public void handle(final ActionEvent event) {
            if (otherUsers.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Please select a user");
                alert.showAndWait();
            } else {
                controllerSimulator.meetSimulator(name, otherUsers.getValue());
                viewToUpdate.updateAllViews();
            }
        }
    };

    private final EventHandler<ActionEvent> delete = new EventHandler<ActionEvent>() {
        @Override
        public void handle(final ActionEvent event) {
            controllerSimulator.removeContact(userIndex, otherUsers.getValue());
            viewToUpdate.updateAllViews();
        }
    };


    Node getGui() {
        return gui;
    }


    /**
     * Constructor.
     * @param listUsers
     * @param view
     * @param controllerSim
     * @param index
     */
    ViewUser(final ArrayList<String> listUsers,
             final ViewApplication view, final ControllerSimulator controllerSim, final int index) {
        viewToUpdate = view;
        controllerSimulator = controllerSim;
        this.userIndex = index;
        this.name = controllerSimulator.getUserId(index);

        ArrayList<String> tmp = new ArrayList<>(listUsers);
        int n = 1;
        int i = 0;
        do {
            if (tmp.get(i).equals(name)) {
                tmp.remove(i);
                n--;
            } else {
                i++;
            }
        } while (n == 1);
        ObservableList<String> obs = FXCollections.observableArrayList(tmp);
        this.otherUsers.setItems(obs);

        this.status = new Label(String.valueOf(controllerSimulator.getUserStatus(i)));
        final Label l = new Label(name);

        final Button btnDeclare = new Button("Declare Infected");
        btnDeclare.setOnAction(this.declare);

        final Button btnMeet = new Button("Meet contact");
        btnMeet.setOnAction(meet);

        final Button btnDelete = new Button("Delete contact");
        btnDelete.setOnAction(delete);


        gui.setStyle("-fx-padding: 10; -fx-border-width: 1;"
                + " -fx-border-radius: 5; -fx-border-color: #505050;");

        gui.getChildren().addAll(
                l, new Separator(),
                new Label("User to meet :"), otherUsers, new Label(""), btnMeet,
                new Label(""), btnDelete, new Label(""),
                new Separator(), new Label("Contacts:"), contacts,
                new Label(""), new Separator(), btnDeclare, status);
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Update the label containing the number of contact for each user.
     */
    public void updateViewUser() {

        contacts.getChildren().clear();
        for (int i = 0; i < controllerSimulator.getUserContacts(userIndex).size(); i++) {
            contacts.getChildren().add(new Label(
                    controllerSimulator.getUserContacts(userIndex).get(i).getUserId().concat(
                            " : ".concat(
                            String.valueOf(
                        controllerSimulator.getUserContacts(userIndex).get(i).getNumberMet())))));
        }
        status.setText(String.valueOf(controllerSimulator.getUserStatus(userIndex)));
    }


    /**
     * Sets status to INFECTED and calls server to notify contacts.
     */
    public void declareMeInfected() {
        controllerSimulator.declareInfected(name);
        viewToUpdate.updateAllViews();
    }
}
