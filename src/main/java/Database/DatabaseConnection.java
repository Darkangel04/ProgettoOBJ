package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection databaseLink;
    public Connection getConnection(){
        String databaseName = "postgres";
        String databaseUser = "martinamele";
        String databasePassword = "1234";
        String url = "jdbc:postgresql://localhost:5432/"+databaseName;

        try{
            Class.forName("org.postgresql.Driver");
            System.out.println("Connessione al database...");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
            System.out.println("Connessione al database riuscita!");
        }catch(Exception e){
            e.printStackTrace();
        }
        return databaseLink;
    }
}