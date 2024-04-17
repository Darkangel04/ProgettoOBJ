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

public class Storico implements Initializable{
    @FXML
    private TableView<Pagina>storicoTableView;
    @FXML
    private TableColumn<Pagina, String>titoloColumn;
    @FXML
    private Button backButton;

    ObservableList<Pagina>PaginaObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resource){
        // Ottiene la connessione al database
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        // Query per ottenere i titoli delle pagine dallo storico dell'autore
        String titStoricoQuery = "SELECT DISTINCT pagina FROM ricerca_storico_autore("+ Controller.idutenteStatic +");";

        try{
            Statement statement = connectDB.createStatement();   // Crea uno statement per eseguire la query
            ResultSet queryOutput = statement.executeQuery(titStoricoQuery);
            while (queryOutput.next()){ // Itera sui risultati della query per salvarli
                String queryTitolo = queryOutput.getString("pagina");

                //Popola la lista visibile all'utente
                PaginaObservableList.add(new Pagina(queryTitolo));
            }

            // Imposta i valori delle colonne della TableView
            titoloColumn.setCellValueFactory(new PropertyValueFactory<>("Titolo"));

            storicoTableView.setItems(PaginaObservableList);

            // Selezionato il titolo, porta alla pagina con tutte le frasi
            storicoTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    Controller.titoloStatic=newSelection.getTitolo();
                }

                try {
                    // Carica PaginaGestStorico.fxml in una nuova finestra
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/unina/project/PaginaGestStorico.fxml"));
                    Parent root = loader.load();

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();

                    // Chiudi la finestra corrente (PaginaStorico)
                    Stage currentStage = (Stage) backButton.getScene().getWindow();
                    currentStage.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });


        }catch(SQLException e){
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }

    }

    public void backButtonOnAction(ActionEvent event) {
        try {
            // Carica HomePage.fxml in una nuova finestra
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/unina/project/HomePage.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Chiudi la finestra corrente (PaginaStorico)
            Stage currentStage = (Stage) backButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
