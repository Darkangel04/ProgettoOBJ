package ImplementazionePostgresDAO;

import Controller.Controller;
import DAO.PaginaDAO;
import Database.DatabaseConnection;
import Model.Frase;
import Model.Pagina;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaginaPostgresDAO implements PaginaDAO {
    public void getIdPaginaString(Pagina P){    // Metodo per ottenere l'ID di una pagina utilizzando il titolo e l'email dell'autore
        // Connessione al database
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String titoloPaginaSelezionata= P.getTitolo();
        String autorePaginaSelezionata= P.getEmail();
        String queryIdpagina = "SELECT idpagina FROM Pagina WHERE titolo =?";
        try {
            // Creazione dello statement ed esecuzione della query
            PreparedStatement preparedStatement = connectDB.prepareStatement(queryIdpagina);
            preparedStatement.setString(1, titoloPaginaSelezionata);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Verifica se il ResultSet contiene una riga valida
            if (resultSet.next()) {
                int idPaginaSelezionata = resultSet.getInt("idpagina");

                // Aggiornamento delle variabili statiche nel controller
                Controller.titoloStatic=titoloPaginaSelezionata;
                Controller.emailPaginaStatic=autorePaginaSelezionata;
                Controller.idpaginaStatic=idPaginaSelezionata;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        public void getIdPaginaLink(Pagina P){  // Metodo per ottenere l'ID di una pagina link utilizzando il titolo
        // Connessione al database
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String queryIdpagina = "SELECT idpagina, idautore FROM Pagina WHERE titolo =?";
        try {
            // Creazione dello statement ed esecuzione della query
            PreparedStatement preparedStatement = connectDB.prepareStatement(queryIdpagina);
            preparedStatement.setString(1, P.getTitolo());
            ResultSet resultSet = preparedStatement.executeQuery();

            // Verifica se il ResultSet contiene una riga valida
            if (resultSet.next()) {
                int idPaginaSelezionata = resultSet.getInt("idpagina");
                int autorePaginaSelezionata = resultSet.getInt("idautore");
                Controller.titoloStatic=P.getTitolo();
                Controller.idautoreStatic=autorePaginaSelezionata;
                Controller.idpaginaStatic=idPaginaSelezionata;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getIdPaginaInt(Pagina P){   // Metodo per ottenere l'ID di una pagina utilizzando il titolo e l'ID dell'autore
        // Connessione al database
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        // Query per ottenere l'ID della pagina utilizzando il titolo
        String queryIdpagina = "SELECT idpagina FROM Pagina WHERE titolo = ?";
        try {
            // Creazione dello statement ed esecuzione della query
            PreparedStatement preparedStatement = connectDB.prepareStatement(queryIdpagina);
            preparedStatement.setString(1, P.getTitolo());
            ResultSet resultSet = preparedStatement.executeQuery();

            // Verifica se il ResultSet contiene una riga valida
            if (resultSet.next()) {
                // Aggiornamento delle variabili statiche nel controller
                Controller.titoloStatic=P.getTitolo();
                Controller.idautoreStatic=P.getIdautore();
                Controller.idpaginaStatic=resultSet.getInt("idpagina");
            }
            System.out.println("fuori");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getIdPaginaTit(Pagina P) {   // Metodo per ottenere l'ID di una pagina utilizzando solo il titolo
        // Connessione al database
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String titoloPaginaSelezionata = P.getTitolo();

        // Query per ottenere l'ID della pagina utilizzando il titolo
        String queryIdpagina = "SELECT idpagina FROM Pagina WHERE titolo =?";
        try {
            // Creazione dello statement ed esecuzione della query
            PreparedStatement preparedStatement = connectDB.prepareStatement(queryIdpagina);
            preparedStatement.setString(1, titoloPaginaSelezionata);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Verifica se il ResultSet contiene una riga valida
            if (resultSet.next()) {
                return resultSet.getInt("idpagina");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean isTitoloNotExist(Pagina P) {   // Metodo per verificare se il titolo della pagina non esiste già
        // Connessione al database
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        // Query per verificare se il titolo esiste già nella tabella Pagina
        String verifyTitolo = " SELECT count(1) FROM Pagina WHERE titolo = '"+ P.getTitolo() +"'";

        try {
            // Creazione dello statement ed esecuzione della query
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyTitolo);

            while(queryResult.next()){
                if(queryResult.getInt(1)==1){
                    return false;
                }else{
                    return true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public void insertNewPage(Pagina P){    // Metodo per inserire una nuova pagina nella tabella Pagina
        // Connessione al database
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        // Query per inserire una nuova riga nella tabella Pagina
        String insertQuery = "INSERT INTO Pagina(Titolo, Idautore) VALUES (?, ?)";
        try (PreparedStatement insertStatement = connectDB.prepareStatement(insertQuery)) { // Creazione dello statement ed esecuzione della query
            insertStatement.setString(1, P.getTitolo().toUpperCase());
            insertStatement.setInt(2, Controller.idutenteStatic);
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isLinkValid(Pagina P) {   // Metodo per verificare se il link della pagina è valido
        // Connessione al database
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        // Query per verificare se il link esiste nella tabella Pagina
        String verifyLink = " SELECT count(1) FROM Pagina WHERE titolo = '"+ P.getTitolo().toUpperCase()+"' ";
        try {
            // Creazione dello statement ed esecuzione della query
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLink);

            while(queryResult.next()){
                if(queryResult.getInt(1)==1){
                    return true;
                }else{
                    return false;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    public void deletePagina(Pagina P){ // Metodo per eliminare una pagina dalla tabella Pagina
        // Connessione al database
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        // Query per eliminare una riga dalla tabella Pagina
        String queryIdpagina = "DELETE FROM Pagina WHERE titolo =?";
        try {
            // Creazione dello statement ed esecuzione della query
            PreparedStatement preparedStatement = connectDB.prepareStatement(queryIdpagina);
            preparedStatement.setString(1, P.getTitolo());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getTesto(Pagina P){   // Metodo per ottenere il testo associato a una pagina
        // Connessione al database
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        // Lista per memorizzare le frasi
        List<String> frasi = new ArrayList<>();

        // Query per ottenere il testo associato alla pagina
        String stringQuery = "SELECT testo FROM ricerca_pagina_visibile("+P.getIdpagina()+");";

        try {
            // Creazione dello statement ed esecuzione della query
            Statement statement = connectDB.createStatement();
            ResultSet resultSet = statement.executeQuery(stringQuery);

            // Iterazione sui risultati del ResultSet e aggiunta dei dati all'ArrayList
            while (resultSet.next()) {
                String testo = resultSet.getString("testo");
                frasi.add(testo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Costruzione del testo concatenato
        StringBuilder testoConcatenato = new StringBuilder();
        for (String testo : frasi) {
            testoConcatenato.append(testo).append("\n ");
        }

        return testoConcatenato.toString();
    }
}
