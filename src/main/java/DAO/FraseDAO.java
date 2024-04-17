package DAO;

import Model.Frase;

public interface FraseDAO {
    public void getIdFrase(Frase F);                        // Metodo per estrarre l'id frase
    public boolean identifySostituzione(Frase F);           // Metodo per identificare se una frase è stata proposta per la sostituzione
    public String getFraseVisibile(Frase F);                // Metodo per estrarre la visibilità di una frase proposta
    public void updateFrase(Frase F, boolean scelta);       // Metodo per aggiornare lo stato di accettazione di una frase
    public void insertNewText(Frase F);                     // Metodo per inserire un nuovo testo nel database
    public void insertFrasePropLink(Frase F);               // Metodo per inserire una nuova frase con un link associato nel database
    public void insertFraseProp(Frase F);                   // Metodo per inserire una nuova proposta nel database
    public void setPosizione(Frase F);                      // Metodo per impostare la posizione di una frase nel database
}
