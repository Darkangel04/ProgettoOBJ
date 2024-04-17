package DAO;

import Model.Utente;

public interface UtenteDAO {
    public boolean validateLogin(Utente U);         // Metodo per validare le credenziali di accesso di un utente
    public boolean  validateRegistration(Utente U); // Metodo per validare la registrazione di un nuovo utente
    public boolean deleteUtente(Utente U);          // Metodo per eliminare un utente dalla tabella Utente
}
