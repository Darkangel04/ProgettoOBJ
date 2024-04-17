package GUI;

import Controller.Controller;
import Database.DatabaseConnection;
import Model.Frase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Proponi implements Initializable{
    @FXML
    private TableView<Frase>FrasiTableView;
    @FXML
    private TableColumn<Frase, String>FrasiColumnView;
    @FXML
    private Label titoloLabel;
    @FXML
    private Button backButton;

    ObservableList<Frase>FraseObservableList = FXCollections.observableArrayList();     // Crea una lista osservabile di Frasi

    @Override
    public void initialize(URL url, ResourceBundle resource){
        // Ottiene la connessione al database
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        titoloLabel.setText(Controller.titoloStatic);

        // Query per ottenere le frasi della pagina visibile
        String frasiQuery = "SELECT testo FROM ricerca_pagina_visibile("+Controller.idpaginaStatic+");";

        try{
            // Crea uno statement per eseguire la query
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(frasiQuery);
            while (queryOutput.next()){ // Itera sui risultati della query per salvarli
                String queryTesto = queryOutput.getString("Testo");

                //Popola la lista visibile all'utente
                FraseObservableList.add(new Frase(queryTesto));
            }

            // Imposta i valori delle colonne della TableView
            FrasiColumnView.setCellValueFactory(new PropertyValueFactory<>("frase"));
            FrasiTableView.setItems(FraseObservableList);

            //Selezionato il titolo, porta alla pagina con le frasi
            FrasiTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    Controller.setPosizione(newSelection.getFrase());       // Imposta la posizione della frase selezionata nel controller

                    try {
                        //Carica PaginaModifica.fxml in una nuova finestra
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/unina/project/PaginaModifica.fxml"));
                        Parent root = loader.load();

                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();

                        // Chiudi la finestra corrente (PaginaProponi)
                        Stage currentStage = (Stage) backButton.getScene().getWindow();
                        currentStage.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }catch(SQLException e){
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }

    public void backButtonOnAction(ActionEvent event) {
        try {
            //Carica PaginaFrasi.fxml in una nuova finestra
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/unina/project/PaginaFrasi.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Chiudi la finestra corrente (PaginaProponi)
            Stage currentStage = (Stage) backButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
