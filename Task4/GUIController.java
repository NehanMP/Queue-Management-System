package com.Task4;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GUIController {
    @FXML
    Button viewCustomers;
    @FXML
    Button searchCustomers;
    @FXML
    Button waitingQueue;
    @FXML
    Button goBack;

    @FXML
    public void viewCustomerQueues() throws IOException {
        Stage stage = (Stage) viewCustomers.getScene().getWindow();
        stage.close();
        Stage initialStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("View_Customers.fxml")));
        initialStage.setTitle("View Customers");
        initialStage.setScene(new Scene(root, 700,500));
        initialStage.show();
    }

    @FXML
    public void searchCustomerView() throws IOException {
        Stage stage = (Stage) searchCustomers.getScene().getWindow();
        stage.close();
        Stage initialStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Search_Customers.fxml")));
        initialStage.setTitle("Search Customers");
        initialStage.setScene(new Scene(root, 700,500));
        initialStage.show();
    }

    @FXML
    public void viewWaitingQueue() throws IOException {
        Stage stage = (Stage) waitingQueue.getScene().getWindow();
        stage.close();
        Stage initialStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("View_Waiting_Queue.fxml")));
        initialStage.setTitle("Waiting Queue");
        initialStage.setScene(new Scene(root, 700,500));
        initialStage.show();
    }

    @FXML
    public void backOption() throws IOException {
        Stage stage = (Stage) goBack.getScene().getWindow();
        stage.close();
        Stage initialStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Menu.fxml")));
        initialStage.setTitle("Main Menu");
        initialStage.setScene(new Scene(root, 700,500));
        initialStage.show();
    }
}