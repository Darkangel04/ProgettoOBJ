package GUI;

import Controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PaginaSceltaProposta implements Initializable{
    @FXML
    private Label avvisoLabel;
    @FXML
    private Button backButton;
    @FXML
    private Button accettaButton;
    @FXML
    private Button rifiutaButton;
    @FXML
    private Text propostaText;
    @FXML
    private Text attualeText;



    @Override
    public void initialize(URL url, ResourceBundle resource) {
        // Imposta il testo della proposta sulla TextArea
        propostaText.setText(Controller.fraseStatic);

        if(!Controller.identifySostituzione()){ // Verifica se l'utente vuole sostituire o inserire la proposta dopo la frase visibile
            avvisoLabel.setText("L'utente vuole inserire la proposta dopo la frase visibile");
        }else{
            avvisoLabel.setText("L'utente vuole sostituire la frase visibile");
        }
        // Ottiene e imposta la frase visibile attuale
        attualeText.setText(Controller.getFraseVisibile());
    }

    public void accettaButtonOnAction(ActionEvent event){
        Controller.updateFrase(true);
        // Carica PaginaGestNotifiche.fxml in una nuova finestra
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/unina/project/PaginaGestNotifiche.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Chiudi la finestra corrente (PaginaSceltaProposta)
            Stage currentStage = (Stage) accettaButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void rifiutaButtonOnAction(ActionEvent event){
        Controller.updateFrase(false);
        // Carica PaginaGestNotifiche.fxml in una nuova finestra
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/unina/project/PaginaGestNotifiche.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Chiudi la finestra corrente (PaginaSceltaProposta)
            Stage currentStage = (Stage) rifiutaButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void backButtonOnAction(ActionEvent event) {
        try {
            // Carica PaginaGestNotifiche.fxml in una nuova finestra
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/unina/project/PaginaGestNotifiche.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Chiudi la finestra corrente (PaginaSceltaProposta)
            Stage currentStage = (Stage) backButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
