package GUI;

import Controller.Controller;
import Database.DatabaseConnection;
import Model.Pagina;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomePage implements Initializable {
    @FXML
    private TableView<Pagina>pagineTableView;
    @FXML
    private TableColumn<Pagina, String>titoloColumn;
    @FXML
    private TableColumn<Pagina, String>autoreColumn;
    @FXML
    private TextField titoloSearch;
    @FXML
    private Button accountButton;
    @FXML
    private Button classificaButton;
    @FXML
    private Button notificheButton;
    @FXML
    private Button storicoButton;
    @FXML
    private Button proposteButton;


    ObservableList<Pagina>PaginaObservableList = FXCollections.observableArrayList();   // Crea una lista osservabile di Pagine

    @Override
    public void initialize(URL url, ResourceBundle resource){
        // Ottiene la connessione al database
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        // Costruisce la query per ottenere i titoli delle pagine e i loro autori
        String titoloQuery = "SELECT titolo, email AS Autore FROM Pagina AS p join Utente AS u on p.idautore=u.idutente ;";

        try{
            Statement statement = connectDB.createStatement();  // Crea uno statement per eseguire la query
            ResultSet queryOutput = statement.executeQuery(titoloQuery);
            while (queryOutput.next()){ // Itera sui risultati della query per salvarli
                String queryTitolo = queryOutput.getString("Titolo");
                String queryAutore = queryOutput.getString("Autore");

                //Popola la lista visibile all'utente
                PaginaObservableList.add(new Pagina(queryTitolo, queryAutore));
            }

            // Imposta i valori delle colonne della TableView
            titoloColumn.setCellValueFactory(new PropertyValueFactory<>("Titolo"));
            autoreColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

            pagineTableView.setItems(PaginaObservableList);

            // Crea un filtro per la ricerca nei titoli delle pagine e nei loro autori
            FilteredList<Pagina> filteredData = new FilteredList<>(PaginaObservableList, b -> true);

            // Aggiorna il filtro quando il testo di ricerca cambia
            titoloSearch.textProperty().addListener((observable, oldValue, newVaule) -> {
               filteredData.setPredicate(Pagina->{
                   if(newVaule.isEmpty() || newVaule.isBlank()){
                       return true;
                   }
                   String searchKeyword = newVaule.toLowerCase();
                   if(Pagina.getTitolo().toLowerCase().contains(searchKeyword)){
                       return true;     //significa che abbiamo trovato un match
                   }else if(Pagina.getEmail().toLowerCase().contains(searchKeyword)){
                       return true;
                   }else{
                       return false; //nessun match trovato
                   }
               });
            });

            SortedList<Pagina> sortedData = new SortedList<>(filteredData); // Ordina i dati filtrati e li associa alla TableView
            sortedData.comparatorProperty().bind(pagineTableView.comparatorProperty());
            pagineTableView.setItems(sortedData);

            // Selezionato il titolo, porta alla pagina con le frasi
            pagineTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    // Ottiene l'ID della pagina selezionata e visualizza le frasi
                    Controller.getIdPaginaString(newSelection.getTitolo(), newSelection.getEmail());
                    Controller.insertView();

                    try {
                        // Carica PaginaFrasi.fxml in una nuova finestra
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/unina/project/PaginaFrasi.fxml"));
                        Parent root = loader.load();

                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();

                        // Chiudi la finestra corrente (HomePage)
                        Stage currentStage = (Stage) accountButton.getScene().getWindow();
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


    public void accountButtonOnAction(ActionEvent event) {
        try {
            // Carica IlMioAccount.fxml in una nuova finestra
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/unina/project/IlMioAccount.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Chiudi la finestra corrente (HomePage)
            Stage currentStage = (Stage) accountButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void classificaButtonOnAction(ActionEvent event) {
        try {
            // Carica PaginaClassifica.fxml in una nuova finestra
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/unina/project/PaginaClassifica.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Chiudi la finestra corrente (HomePage)
            Stage currentStage = (Stage) classificaButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void notificheButtonOnAction(ActionEvent event) {
        try {
            // Carica PaginaNotifiche.fxml in una nuova finestra
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/unina/project/PaginaNotifiche.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Chiudi la finestra corrente (HomePage)
            Stage currentStage = (Stage) notificheButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void storicoButtonOnAction(ActionEvent event) {
        try {
            // Carica PaginaStorico.fxml in una nuova finestra
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/unina/project/PaginaStorico.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Chiudi la finestra corrente (HomePage)
            Stage currentStage = (Stage) storicoButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void proposteButtonOnAction(ActionEvent event) {
        try {
            // Carica PaginaNotifiche.fxml in una nuova finestra
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/unina/project/PaginaLeMieProposte.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Chiudi la finestra corrente (HomePage)
            Stage currentStage = (Stage) proposteButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
