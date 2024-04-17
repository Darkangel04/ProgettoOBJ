package ImplementazionePostgresDAO;

import Controller.Controller;
import DAO.UtenteDAO;
import Database.DatabaseConnection;
import Model.Utente;

import java.sql.*;

public class UtentePostgresDAO implements UtenteDAO {
    public boolean validateLogin(Utente U){  // Metodo per validare le credenziali di accesso di un utente
        // Connessione al database
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        // Recupero username e password dall'oggetto Utente
        String username = U.getEmail();
        String password = U.getPassword();

        // Query per verificare le credenziali di accesso
        String verifyLogin = " SELECT count(1) FROM Utente WHERE email = '"+ username +"' AND password = '"+ password +"' ";
        String queryId = " SELECT idutente FROM Utente WHERE email = '"+ username +"' AND password = '"+ password +"' ";

        try {
            // Creazione dello statement ed esecuzione della query
            Statement statement = connectDB.createStatement();
            ResultSet queryResult2 = statement.executeQuery(queryId);
            while (queryResult2.next()){
                // Aggiornamento delle variabili statiche nel controller con l'ID e l'email dell'utente autenticato
                Controller.email=username;
                Controller.idutenteStatic =queryResult2.getInt("idutente");
                Controller.password=password;
            }
            queryResult2.close();

            // Esecuzione della query per verificare le credenziali di accesso
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while(queryResult.next()){
                if(queryResult.getInt(1)==1){
                    return true;
                }else{
                    return false;
                }
            }
            queryResult.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean validateRegistration(Utente U){  // Metodo per validare la registrazione di un nuovo utente
        // Connessione al database
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        // Recupero username e password dall'oggetto Utente
        String username = U.getEmail();
        String password = U.getPassword();

        // Query per verificare se l'username è già in uso
        String verifyLogin = " SELECT count(1) FROM Utente WHERE email = '"+ username +"'";

        try {
            // Creazione dello statement ed esecuzione della query
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if(queryResult.getInt(1)==1){
                    return false;   // Se l'username è già in uso, restituisce false
                }else{
                    // Se l'username non è in uso, inserisce un nuovo record nella tabella Utente
                    String insertQuery = "INSERT INTO Utente (Email, Password) VALUES (?, ?)";
                    try (PreparedStatement insertStatement = connectDB.prepareStatement(insertQuery)) {
                        insertStatement.setString(1, username);
                        insertStatement.setString(2, password);
                        insertStatement.executeUpdate();
                        return true;    // Restituisce true se la registrazione è avvenuta con successo

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteUtente(Utente U){  // Metodo per eliminare un utente dalla tabella Utente
        // Connessione al database
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        // Query per eliminare l'utente
        String deleteLogin = " DELETE FROM Utente WHERE idutente = "+ U.getIdutente() +";";
        try {
            // Creazione dello statement ed esecuzione della query
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(deleteLogin);
            return true;     // Restituisce true se l'eliminazione è avvenuta con successo
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
