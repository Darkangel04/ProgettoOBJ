package GUI;

import Controller.Controller;
import Database.DatabaseConnection;
import Model.Pagina;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Classifica implements Initializable{

    @FXML
    private TableView<Pagina>classificaTableView;
    @FXML
    private TableColumn<Pagina, String>titoloColumn;
    @FXML
    private TableColumn<Pagina, String>autoreColumn;
    @FXML
    private TableColumn<Pagina, String>visualColumn;
    @FXML
    private Button backButton;

    ObservableList<Pagina>PaginaObservableList = FXCollections.observableArrayList();   // Crea una lista osservabile di Pagine

    @Override
    public void initialize(URL url, ResourceBundle resource){   // Metodo chiamato quando il controller viene inizializzato
        DatabaseConnection connectNow = new DatabaseConnection();   // Ottiene una connessione al database
        Connection connectDB = connectNow.getConnection();

        // Query per recuperare i dati della classifica delle pagine
        String classificaQuery = "SELECT * FROM classifica_pagine;";

        try{
            Statement statement = connectDB.createStatement();   // Crea uno statement per eseguire la query
            ResultSet queryOutput = statement.executeQuery(classificaQuery);

            while (queryOutput.next()){ // Itera sui risultati della query per salvarli
                int queryVisual = queryOutput.getInt("Visualizzazioni");
                String queryTitolo = queryOutput.getString("Titolo");
                String queryAutore = queryOutput.getString("Autore");

                //Popola la lista visibile all'utente
                PaginaObservableList.add(new Pagina(queryVisual,queryTitolo, queryAutore));
            }

            // Imposta i valori delle colonne della TableView
            visualColumn.setCellValueFactory(new PropertyValueFactory<>("numVisite"));
            titoloColumn.setCellValueFactory(new PropertyValueFactory<>("Titolo"));
            autoreColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));

            classificaTableView.setItems(PaginaObservableList);

            // Aggiunge un listener per gestire la selezione di una riga nella TableView
            classificaTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    // Recupera l'ID della pagina selezionata e aggiorna le visualizzazioni
                    Controller.getIdPaginaInt(newSelection.getTitolo(), newSelection.getIdautore());
                    Controller.insertView();

                    try {
                        // Carica la PaginaFrasi.fxml in una nuova finestra
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/unina/project/PaginaFrasi.fxml"));
                        Parent root = loader.load();

                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();

                        // Chiudi la finestra corrente (HomePage)
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
            // Carica la HomePage.fxml in una nuova finestra
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/unina/project/HomePage.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Chiudi la finestra corrente (PaginaClassifica)
            Stage currentStage = (Stage) backButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

