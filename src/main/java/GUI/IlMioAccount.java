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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;



public class IlMioAccount implements Initializable{

    @FXML
    private TableView<Pagina>pagineCreateTable;
    @FXML
    private TableColumn<Pagina,String>pagineCreateColumn;
    @FXML
    private Label idLabel;
    @FXML
    private Label nicknameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Button newPageButton;
    @FXML
    private Button backButton;
    @FXML
    private Button eliminaAccountButton;
    @FXML
    private Button eliminapagButton;


    ObservableList<Pagina>PaginaObservableList = FXCollections.observableArrayList();   // Crea una lista osservabile di Pagine


    @Override
    public void initialize(URL url, ResourceBundle resource){
        // Ottiene la connessione al database
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        // Imposta i valori delle etichette con i dati dell'utente
        idLabel.setText(String.valueOf(Controller.idutenteStatic));
        nicknameLabel.setText(Controller.email);
        passwordLabel.setText(Controller.password);

        // Costruisce la query per ottenere i titoli delle pagine create dall'utente
        String titoloQuery = "SELECT titolo FROM Pagina WHERE idautore ="+ Controller.idutenteStatic + ";";

        try{
            Statement statement = connectDB.createStatement();  // Crea uno statement per eseguire la query
            ResultSet queryOutput = statement.executeQuery(titoloQuery);
            while (queryOutput.next()){ // Itera sui risultati della query per salvarli
                String queryTitolo = queryOutput.getString("Titolo");

                //Popola la lista visibile all'utente
                PaginaObservableList.add(new Pagina(queryTitolo));
            }

            // Imposta i valori delle colonne della TableView
            pagineCreateColumn.setCellValueFactory(new PropertyValueFactory<>("Titolo"));
            // Imposta la lista di Pagine nella TableView
            pagineCreateTable.setItems(PaginaObservableList);

            // Selezionato il titolo, porta alla pagina con le frasi
            pagineCreateTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) { // Ottiene l'ID della pagina selezionata e visualizza le frasi
                    System.out.println(Controller.idutenteStatic);
                    Controller.getIdPaginaInt(newSelection.getTitolo(), Controller.idutenteStatic);
                    Controller.insertView();

                    try {
                        // Carica PaginaFrasi.fxml in una nuova finestra
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/unina/project/PaginaFrasi.fxml"));
                        Parent root = loader.load();

                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();

                        // Chiudi la finestra corrente (IlMioAccount)
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
            // Carica HomePage.fxml in una nuova finestra
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/unina/project/HomePage.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Chiudi la finestra corrente (IlMioAccount)
            Stage currentStage = (Stage) backButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void eliminaAccountButtonOnAction(ActionEvent event) throws SQLException {
        if(Controller.deleteUtente()){     // Prova ad eliminare l'account utente
            // Carica EliminaAccount.fxml in una nuova finestra
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/unina/project/EliminaAccount.fxml"));
                Parent root = loader.load();

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();

                // Chiudi la finestra corrente (IlMioAccount)
                Stage currentStage = (Stage) eliminaAccountButton.getScene().getWindow();
                currentStage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Errore nell'eliminazione dell'utente.");
        }

    }

    public void newPageButtonOnAction(ActionEvent event) {
        try {
            // Carica PaginaNuovaPagina.fxml in una nuova finestra
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/unina/project/PaginaNuovaPagina.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Chiudi la finestra corrente (IlMioAccount)
            Stage currentStage = (Stage) newPageButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void eliminapagButtonOnAction(ActionEvent event) {
        try {
            // Carica PaginaEliminaPagina.fxml in una nuova finestra
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/unina/project/PaginaEliminaPagina.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Chiudi la finestra corrente (IlMioAccount)
            Stage currentStage = (Stage) eliminapagButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
