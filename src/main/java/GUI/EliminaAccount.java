package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class EliminaAccount {
    @FXML
    private Button backLoginButton;

    public void backLoginButtonOnAction(ActionEvent event) {
        try {
            // Carica la PaginaLogin.fxml in una nuova finestra
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/unina/project/PaginaLogin.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Chiudi la finestra corrente (EliminaAccount)
            Stage currentStage = (Stage) backLoginButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
