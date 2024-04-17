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

public class GestStorico implements Initializable{

    @FXML
    private TableView<Frase>storicoTableView;
    @FXML
    private TableColumn<Frase, String>fraseColumn;
    @FXML
    private TableColumn<Frase, String>visibileColumn;
    @FXML
    private TableColumn<Frase, String>accettataColumn;
    @FXML
    private TableColumn<Frase, String>sostituzioneColumn;
    @FXML
    private Button backButton;

    ObservableList<Frase>FraseObservableList = FXCollections.observableArrayList(); // Crea una lista osservabile di Frasi

    @Override
    public void initialize(URL url, ResourceBundle resource){
        // Ottiene la connessione al database
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String titStoricoQuery = "SELECT testo, visibile, accettata, sostituzione FROM ricerca_storico_autore("+ Controller.idutenteStatic +");";

        try{
            Statement statement = connectDB.createStatement();  // Crea uno statement per eseguire la query
            ResultSet queryOutput = statement.executeQuery(titStoricoQuery);
            while (queryOutput.next()){ // Itera sui risultati della query per salvarli
                String queryTesto = queryOutput.getString("testo");
                boolean queryVisibile = queryOutput.getBoolean("visibile");
                boolean queryAccettata = queryOutput.getBoolean("accettata");
                boolean querySostituzione = queryOutput.getBoolean("sostituzione");

                //Popola la lista visibile all'utente
                FraseObservableList.add(new Frase(queryTesto,queryVisibile,queryAccettata,querySostituzione));
            }

            // Imposta i valori delle colonne della TableView
            fraseColumn.setCellValueFactory(new PropertyValueFactory<>("frase"));
            visibileColumn.setCellValueFactory(new PropertyValueFactory<>("Visibile"));
            accettataColumn.setCellValueFactory(new PropertyValueFactory<>("Accettata"));
            sostituzioneColumn.setCellValueFactory(new PropertyValueFactory<>("Sostituzione"));

            storicoTableView.setItems(FraseObservableList);


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

            // Chiudi la finestra corrente (PaginaGestStorico)
            Stage currentStage = (Stage) backButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
