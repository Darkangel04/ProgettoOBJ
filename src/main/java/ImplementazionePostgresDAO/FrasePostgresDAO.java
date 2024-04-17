package ImplementazionePostgresDAO;

import Controller.Controller;
import DAO.FraseDAO;
import Database.DatabaseConnection;
import GUI.HomePage;
import Model.Frase;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FrasePostgresDAO implements FraseDAO {
    public void getIdFrase(Frase F){    // Metodo per estrarre l'id frase
        // Connessione al database
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        // Query per ottenere l'ID della frase
        String idfraseQuery = "SELECT idfrase  FROM proposte_autore('" + Controller.idutenteStatic + "')WHERE proposta='" + F.getFrase() + "';";
        try{
            // Creazione dello statement ed esecuzione della query
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(idfraseQuery);

            while(queryOutput.next()){  // Estrazione dell'ID della frase
                Controller.idfraseStatic=queryOutput.getInt("idfrase");
                Controller.fraseStatic=F.getFrase();
            }

        }catch(SQLException e){
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }

    public boolean identifySostituzione(Frase F){   // Metodo per identificare se una frase è stata proposta per la sostituzione
        // Connessione al database
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        // Query per identificare se la frase è stata proposta per la sostituzione
        String fraseQuery = "SELECT sostituzione FROM proposte_autore('" + Controller.idutenteStatic + "') WHERE proposta='" + F.getFrase() + "';";
        try{
            // Creazione dello statement ed esecuzione della query
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(fraseQuery);

            while(queryOutput.next()){  // Estrazione del valore di sostituzione
                boolean querySost = queryOutput.getBoolean("sostituzione");
                if(!querySost){
                    return false;
                }else{
                    return true;
                }
            }

        }catch(SQLException e){
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        return false;
    }

    public String getFraseVisibile(Frase F){    // Metodo per estrarre la visibilità di una frase proposta
        // Connessione al database
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        // Query per ottenere la visibilità della frase proposta
        String fraseQuery = "SELECT visibile FROM proposte_autore('" + Controller.idutenteStatic + "') WHERE proposta='" + F.getFrase() + "';";
        try{
            // Creazione dello statement ed esecuzione della query
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(fraseQuery);

            while(queryOutput.next()){  // Estrazione del valore di visibilità
                return queryOutput.getString("visibile");
            }

        }catch(SQLException e){
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        return null;
    }

    public void updateFrase(Frase F, boolean scelta){   // Metodo per aggiornare lo stato di accettazione di una frase
       try{
           // Connessione al database
           DatabaseConnection connectNow = new DatabaseConnection();
           Connection connectDB = connectNow.getConnection();

           // Aggiornamento dello stato di accettazione della frase
           if(scelta){
               String updateQuery = "UPDATE Frase SET accettata = true WHERE idfrase = "+Controller.idfraseStatic+";";
               // Creazione dello statement ed esecuzione della query
               PreparedStatement preparedStatement = connectDB.prepareStatement(updateQuery);
               preparedStatement.executeUpdate();
           }else{
               String updateQuery = "UPDATE Frase SET accettata = false WHERE idfrase = "+Controller.idfraseStatic+";";
               // Creazione dello statement ed esecuzione della query
               PreparedStatement preparedStatement = connectDB.prepareStatement(updateQuery);
               preparedStatement.executeUpdate();
           }
       } catch (SQLException e) {
           System.out.println("Errore durante l'aggiornamento del database: " + e.getMessage());
           e.printStackTrace();
       }
    }

    public void insertNewText(Frase F) {    // Metodo per inserire un nuovo testo nel database
        // Connessione al database
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        // Query per inserire un nuovo testo
        String testoQuery = "SELECT ins_frasi_originali(?, ?, ?)";
        try (PreparedStatement testoStatement = connectDB.prepareStatement(testoQuery)) {   // Creazione dello statement ed esecuzione della query
            testoStatement.setString(1, F.getFrase());
            testoStatement.setInt(2, Controller.idpaginaStatic);
            testoStatement.setInt(3, Controller.idutenteStatic);
            testoStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertFrasePropLink(Frase F){   // Metodo per inserire una nuova frase con un link associato nel database
        // Connessione al database
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        // Query per inserire una nuova frase con un link associato
        String insertQuery = "INSERT INTO Frase(Stringa, Posizione, IdPagina, IdUtente, IdLink, Sostituzione) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement insertStatement = connectDB.prepareStatement(insertQuery)) { // Creazione dello statement ed esecuzione della query
            insertStatement.setString(1, F.getFrase());
            insertStatement.setInt(2, F.getPosizione());
            insertStatement.setInt(3, F.getIdpagina());
            insertStatement.setInt(4, F.getIdutente());
            insertStatement.setInt(5, F.getIdlink());
            insertStatement.setBoolean(6, F.getSostituzione());
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertFraseProp(Frase F){   // Metodo per inserire una nuova proposta nel database
        // Connessione al database
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        // Query per inserire una nuova proposta
        String insertQuery = "INSERT INTO Frase(Stringa, Posizione, IdPagina, IdUtente, Sostituzione) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement insertStatement = connectDB.prepareStatement(insertQuery)) {     // Creazione dello statement ed esecuzione della query
            insertStatement.setString(1, F.getFrase());
            insertStatement.setInt(2, F.getPosizione());
            insertStatement.setInt(3, F.getIdpagina());
            insertStatement.setInt(4, F.getIdutente());
            insertStatement.setBoolean(5, F.getSostituzione());
            insertStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setPosizione(Frase F){  // Metodo per impostare la posizione di una frase nel database
        // Connessione al database
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        // Query per ottenere la posizione della frase
        String queryPosizione = "SELECT posizione FROM Frase WHERE stringa =?";
        try {
            // Creazione dello statement ed esecuzione della query
            PreparedStatement preparedStatement = connectDB.prepareStatement(queryPosizione);
            preparedStatement.setString(1, F.getFrase());
            ResultSet resultSet = preparedStatement.executeQuery();

            // Verifica se il ResultSet contiene una riga valida
            if (resultSet.next()) {
                Controller.posizioneStatic=resultSet.getInt("posizione");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
