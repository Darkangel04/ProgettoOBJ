package DAO;

import Model.Pagina;

public interface PaginaDAO {
    public void getIdPaginaString(Pagina P);        // Metodo per ottenere l'ID di una pagina utilizzando il titolo e l'email dell'autore
    public void getIdPaginaInt(Pagina P);           // Metodo per ottenere l'ID di una pagina utilizzando il titolo e l'ID dell'autore
    public int getIdPaginaTit(Pagina P);            // Metodo per ottenere l'ID di una pagina utilizzando solo il titolo
    public  boolean isTitoloNotExist(Pagina P);     // Metodo per verificare se il titolo della pagina non esiste già
    public void insertNewPage(Pagina P);            // Metodo per inserire una nuova pagina nella tabella Pagina
    public boolean isLinkValid(Pagina P);           // Metodo per verificare se il link della pagina è valido
    public void deletePagina(Pagina P);             // Metodo per eliminare una pagina dalla tabella Pagina
    public String getTesto(Pagina P);               // Metodo per ottenere il testo associato a una pagina
    public void getIdPaginaLink(Pagina P);          // Metodo per ottenere l'ID di una pagina link utilizzando il titolo
}
