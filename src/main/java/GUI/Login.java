package GUI;

import Controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;

import java.io.IOException;

public class Login {
    public Button accediButton;
    @FXML
    private Button registratiButton;
    @FXML
    private Label loginMessage;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;



    public void accediButtonOnAction(ActionEvent event){
        if(!usernameTextField.getText().isBlank() && !passwordTextField.getText().isBlank()){   // Verifica se entrambi i campi di testo non sono vuoti
            if(!Controller.validateLogin(usernameTextField.getText(),passwordTextField.getText())){      // Verifica se l'accesso è valido
                loginMessage.setText("Password o Email errati!");
            }else{
                try {
                    // Carica HomePage.fxml in una nuova finestra
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/unina/project/HomePage.fxml"));
                    Parent root = loader.load();

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();

                    // Chiudi la finestra corrente (Login)
                    Stage currentStage = (Stage) registratiButton.getScene().getWindow();
                    currentStage.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else{
            loginMessage.setText("Inserisci Email e Password!");
        }
    }

    //Va alla pagina di Registrazione
    public void registratiButtonOnAction(ActionEvent event) {
        try {
            // Carica PaginaRegistrazione.fxml in una nuova finestra
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/unina/project/PaginaRegistrazione.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Chiudi la finestra corrente (Login)
            Stage currentStage = (Stage) registratiButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}