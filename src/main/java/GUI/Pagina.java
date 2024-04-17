package GUI;

import Controller.Controller;
import Database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Pagina implements Initializable{

    @FXML
    private Button proponiButton;
    @FXML
    private Text contTestoText;
    @FXML
    private Label titoloLabel;
    @FXML
    private Button backButton;
    @FXML
    private ComboBox<Model.Pagina> linkComboBox;

    ObservableList<Model.Pagina> pagineList = FXCollections.observableArrayList();  // Crea una lista osservabile di Pagine

    @Override
    public void initialize(URL url, ResourceBundle resource){
        // Ottiene la connessione al database
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        //Titolo della pagina
        titoloLabel.setText(Controller.titoloStatic);
        // Creazione del testo concatenato
        contTestoText.setText(Controller.getTesto());

        // Query per recuperare i link visibili relativi alla pagina corrente
        String queryLink = "SELECT l.idlink, p.titolo FROM frase as l JOIN pagina as p ON l.idlink=p.idpagina WHERE l.visibile AND l.idpagina ="+Controller.idpaginaStatic+";";

        try {
            Statement statement = connectDB.createStatement();  // Crea uno statement per eseguire la query
            ResultSet resultSet = statement.executeQuery(queryLink);
            while (resultSet.next()) {  // Itera sui risultati della query per salvarli
                String titolo = resultSet.getString("titolo");

                // Aggiungi la pagina recuperata dalla query al ComboBox
                pagineList.add(new Model.Pagina(titolo));
            }

            // Imposta gli elementi del ComboBox con la lista di pagine
            linkComboBox.setItems(pagineList);

        }catch (SQLException e) {
            e.printStackTrace();
        }

        // Gestisce la selezione di un elemento nel ComboBox (link)
        linkComboBox.setOnAction(e -> {
            Model.Pagina paginaSelezionata = linkComboBox.getValue();   // Ottiene la pagina selezionata
            Controller.getIdPaginaLink(paginaSelezionata.getTitolo());  // Ottiene l'ID della pagina selezionata e lo salva nel controller
            Controller.insertView();    // Inserisce la visita nel database

            try {
                // Carica PaginaFrasi.fxml in una nuova finestra
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/unina/project/PaginaFrasi.fxml"));
                Parent root = loader.load();

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();

                // Chiudi la finestra corrente (Pagina)
                Stage currentStage = (Stage) proponiButton.getScene().getWindow();
                currentStage.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

    }

    public void proponiButtonOnAction(ActionEvent event) {
        try {
            // Carica PaginaProponi.fxml in una nuova finestra
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/unina/project/PaginaProponi.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Chiudi la finestra corrente (Pagina)
            Stage currentStage = (Stage) proponiButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
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

            // Chiudi la finestra corrente (Pagina)
            Stage currentStage = (Stage) backButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
