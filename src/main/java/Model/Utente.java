package Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utente {
    public  int idutente;
    public  String email;
    public  String password;

    public Utente(int idutente, String email, String password) {
        this.idutente = idutente;
        this.email = email;
        this.password = password;
    }

    public Utente(String email, String password){
        this.email =email;
        this.password =password;
    }

    public Utente(int idutente){
        this.idutente =idutente;
    }

    public int getIdutente() {
        return idutente;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setIdutente(int idutente) {
        this.idutente = idutente;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    //Metodo per verificare se l'email è nel formato corretto
    public static boolean isValidEmail(String email) {
        // Definizione del pattern per l'email
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        // Creazione dell'oggetto Pattern
        Pattern pattern = Pattern.compile(emailRegex);

        // Creazione dell'oggetto Matcher
        Matcher matcher = pattern.matcher(email);

        // Verifica della corrispondenza del pattern
        return matcher.matches();
    }
}
