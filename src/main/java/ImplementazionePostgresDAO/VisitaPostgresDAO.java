package ImplementazionePostgresDAO;

import DAO.VisitaDAO;
import Database.DatabaseConnection;
import Model.Visita;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class VisitaPostgresDAO implements VisitaDAO {
    public void insertView(Visita V){   // Metodo per inserire una nuova visita nel database
        // Connessione al database
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        try {
            // Creazione dello statement ed esecuzione dell'inserimento nella tabella Visita
            Statement statement = connectDB.createStatement();
            String insert = "INSERT INTO Visita(IdPagina, IdUtente) VALUES (" + V.getIdpagina() + ", " + V.getIdutente() + ")";
            statement.executeUpdate(insert);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
