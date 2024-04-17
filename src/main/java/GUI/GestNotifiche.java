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

public class GestNotifiche implements Initializable {
    @FXML
    private TableView<Frase>proposteTableView;
    @FXML
    private TableColumn<Frase, String>propostaColumn;
    @FXML
    private Button backButton;

    ObservableList<Frase>FraseObservableList = FXCollections.observableArrayList(); // Crea una lista osservabile di Frasi

    @Override
    public void initialize(URL url, ResourceBundle resource){
        // Ottiene la connessione al database
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        // Costruisce la query per ottenere le proposte dell'autore per una pagina specifica
        String propostaQuery = "SELECT proposta FROM proposte_autore('" + Controller.idutenteStatic + "') WHERE pagina='" + Controller.titoloStatic + "';";

        try{
            Statement statement = connectDB.createStatement();  // Crea uno statement per eseguire la query
            ResultSet queryOutput = statement.executeQuery(propostaQuery);
            while (queryOutput.next()){ // Itera sui risultati della query per salvarli
                String queryPropsta = queryOutput.getString("proposta");

                //Popola la lista visibile all'utente
                FraseObservableList.add(new Frase(queryPropsta));
            }

            // Imposta i valori delle colonne della TableView
            propostaColumn.setCellValueFactory(new PropertyValueFactory<>("frase"));

            proposteTableView.setItems(FraseObservableList);

            // Aggiunge un listener alla selezione della TableView per gestire il clic sulle proposte
            proposteTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    // Ottiene l'ID della frase selezionata
                    Controller.getIdFrase(newSelection.getFrase());

                    try {
                        // Carica PaginaSceltaProposta.fxml in una nuova finestra
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/unina/project/PaginaSceltaProposta.fxml"));
                        Parent root = loader.load();

                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();

                        // Chiudi la finestra corrente (PaginaGestNotifiche)
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
            // Carica PaginaNotifiche.fxml in una nuova finestra
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/unina/project/PaginaNotifiche.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Chiudi la finestra corrente (PaginaGestNotifiche)
            Stage currentStage = (Stage) backButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
