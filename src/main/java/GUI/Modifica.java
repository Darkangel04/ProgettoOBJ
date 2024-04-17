package GUI;

import Controller.Controller;
import Model.Frase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Modifica implements Initializable {

    @FXML
    private Label titoloLabel;
    @FXML
    private Label modificaMessage;
    @FXML
    private Label linkMessage;
    @FXML
    private Button backButton;
    @FXML
    private Button modificaButton;
    @FXML
    private Button aggiungiButton;
    @FXML
    private TextArea fraseTextArea;
    @FXML
    private TextField linkTextField;


    public void initialize(URL url, ResourceBundle resource) {
        titoloLabel.setText(Controller.titoloStatic);
    }

    public void modificaButtonOnAction(ActionEvent event) {
        String frase = fraseTextArea.getText();
        String link = linkTextField.getText();

        if (Frase.isTextAreaValid(frase)) {
            if (Controller.isLinkValid(link) || linkTextField.getText().isEmpty()) {
                if(linkTextField.getText().isEmpty()){
                    Controller.insertFraseProp(true,frase);
                    linkMessage.setText("Link valido!");
                    modificaMessage.setText("La frase è stata proposta con successo!");
                }else{
                    Controller.insertFrasePropLink(true,frase,Controller.getIdPaginaTit(link.toUpperCase()));
                    linkMessage.setText("Link valido!");
                    modificaMessage.setText("La frase è stata proposta con successo!");
                }
                try {
                    // Introduci un ritardo di 4 secondi (4000 millisecondi) prima di cambiare pagina
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/unina/project/Homepage.fxml"));
                    Parent root = loader.load();

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();

                    // Chiudi la finestra corrente (PaginaModifica)
                    Stage currentStage = (Stage) modificaButton.getScene().getWindow();
                    currentStage.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if(!Controller.isLinkValid(link)) {
                linkMessage.setText("Link non valido");
            }
        }else{
            modificaMessage.setText("La frase è vuota o non contiene alcun carattere dall'A alla Z.");
        }
    }


    public void aggiungiButtonOnAction(ActionEvent event) {
        String frase = fraseTextArea.getText();
        String link = linkTextField.getText();

        if (Frase.isTextAreaValid(frase)) {     // Verifica se la frase è valida e se il link è valido o vuoto
            if (Controller.isLinkValid(link) || linkTextField.getText().isEmpty()) {
                if(linkTextField.getText().isEmpty()){      // Se il link è vuoto, inserisce solo la frase
                    Controller.insertFraseProp(false,frase);
                    linkMessage.setText(" ");
                    modificaMessage.setText("La frase è stata proposta con successo!");
                }else{      // Se il link non è vuoto, inserisce la frase con il link
                    if(frase.toUpperCase().contains(link.toUpperCase())){
                        Controller.insertFrasePropLink(false,frase,Controller.getIdPaginaTit(link.toUpperCase()));
                        linkMessage.setText("Link valido!");
                        modificaMessage.setText("La frase è stata proposta con successo!");
                    }else{      // Se la frase non contiene il titolo link, segnala il link come non valido
                        linkMessage.setText("Link non valido!");
                    }
                }
                try {
                    // Introduci un ritardo di 4 secondi (4000 millisecondi) prima di cambiare pagina
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    // Carica Homepage.fxml in una nuova finestra
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/unina/project/Homepage.fxml"));
                    Parent root = loader.load();

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();

                    // Chiudi la finestra corrente (PaginaModifica)
                    Stage currentStage = (Stage) aggiungiButton.getScene().getWindow();
                    currentStage.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if(!Controller.isLinkValid(link)) {
                linkMessage.setText("Link non valido");
            }
        }else{
            modificaMessage.setText("La frase è vuota o non contiene alcun carattere dall'A alla Z.");
        }
    }

    public void backButtonOnAction(ActionEvent event) {
        try {
            // Carica PaginaProponi.fxml in una nuova finestra
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/unina/project/PaginaProponi.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Chiudi la finestra corrente (PaginaModifica)
            Stage currentStage = (Stage) backButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

