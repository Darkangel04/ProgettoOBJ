package GUI;

import Controller.Controller;
import Model.Utente;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;

public class Registra {


    @FXML
    private Button backButton;
    @FXML
    private TextField regEmail;
    @FXML
    private PasswordField regPassword;
    @FXML
    private PasswordField regCpassword;
    @FXML
    private Label registerMessage;

    public void pRegistratiButtonOnAction(){
        if(!regEmail.getText().isBlank() && !regPassword.getText().isBlank() && !regCpassword.getText().isBlank()){     // Verifica se i campi email, password e conferma password non sono vuoti
            if(regCpassword.getText().equals(regPassword.getText())){      // Verifica se la conferma password coincide con la password
                if(Utente.isValidEmail(regEmail.getText())){    // Verifica se l'email è valida
                    if(Controller.validateRegistration(regEmail.getText(),regPassword.getText())){  // Verifica se la registrazione è valida tramite il controller
                        registerMessage.setText("Registrazione effettuata con successo!");
                    }else{
                        registerMessage.setText("Esiste già un account con questa mail!");
                    }

                }else{
                    registerMessage.setText("L'email non è valida!");
                }
            }else{
                registerMessage.setText("La password non coincide!");
            }
        }else{
            registerMessage.setText("Inserisci Email e Password!");
        }
    }



    public void backButtonOnAction() {
        try {
            //Carica PaginaLogin.fxml in una nuova finestra
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/unina/project/PaginaLogin.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Chiudi la finestra corrente (Registrati)
            Stage currentStage = (Stage) backButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
