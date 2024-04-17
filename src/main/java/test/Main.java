package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Carica l'interfaccia utente dall'FXML e imposta la radice
        Parent root = FXMLLoader.load(getClass().getResource("/unina/project/PaginaLogin.fxml"));
        primaryStage.setTitle("Pagina iniziale");
        primaryStage.setScene(new Scene(root,520, 400));
        primaryStage.show();
    }

    public static void main(String[]args){
        launch(args);  // Avvia l'applicazione JavaFX chiamando il metodo launch() e passando gli argomenti
    }
}