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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NuovaPagina implements Initializable {
    @FXML
    private TextArea frasiText;
    @FXML
    private TextField titoloTextField;
    @FXML
    private Button backButton;
    @FXML
    private Button condividiButton;
    @FXML
    private Label errorLabel;


    @Override
    public void initialize(URL url, ResourceBundle resource){
        // Non è necessario eseguire alcuna operazione in questo caso
    }


    public void backButtonOnAction(ActionEvent event) {
        try {
            // Carica IlMioAccount.fxml in una nuova finestra
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/unina/project/IlMioAccount.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Chiudi la finestra corrente (NuovaPaginae)
            Stage currentStage = (Stage) backButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void condividiButtonOnAction(ActionEvent event) {
        String titolo = titoloTextField.getText();
        String testo = frasiText.getText();
        if (Frase.isTextAreaValid(titolo)) {    // Verifica se il titolo è valido
            if (Controller.isTitoloNotExist(titolo)) {  // Verifica se il titolo non esiste già
                if (Frase.isTextAreaValid(testo)) {  // Verifica se il testo è valido
                    Controller.insertNewPage(titolo);   // Inserisce la nuova pagina nel database
                    Controller.getIdPaginaInt(titolo,Controller.idutenteStatic);    // Ottiene l'ID della pagina appena creata e l'ID dell'utente corrente
                    Controller.insertNewText(testo);    // Inserisce il testo della nuova pagina nel database
                    try {
                        // Carica IlMioAccount.fxml in una nuova finestra
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/unina/project/IlMioAccount.fxml"));
                        Parent root = loader.load();

                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();

                        // Chiudi la finestra corrente (NuovaPagina)
                        Stage currentStage = (Stage) condividiButton.getScene().getWindow();
                        currentStage.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    errorLabel.setText("Il testo non è valido");
                }
            }else {
                errorLabel.setText("Il titolo già esiste");
            }
        }else {
            errorLabel.setText("Il titolo non è valido");
        }
    }
}