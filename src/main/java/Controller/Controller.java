package Controller;

import ImplementazionePostgresDAO.FrasePostgresDAO;
import ImplementazionePostgresDAO.PaginaPostgresDAO;
import ImplementazionePostgresDAO.UtentePostgresDAO;
import ImplementazionePostgresDAO.VisitaPostgresDAO;
import Model.Frase;
import Model.Pagina;
import Model.Utente;
import Model.Visita;

public class Controller {
    public static int idfraseStatic;
    public static String fraseStatic;
    public static int posizioneStatic;
    public static String titoloStatic;
    public static int idautoreStatic;
    public static String emailPaginaStatic;
    public static int idpaginaStatic;
    public static int idutenteStatic;
    public static String email;
    public static String password;

    //UTENTE
    public static boolean validateLogin(String username, String password){
        Utente U = new Utente(username, password);
        UtentePostgresDAO UD = new UtentePostgresDAO();
        return UD.validateLogin(U);
    }
    public static boolean validateRegistration(String username, String password){
        Utente U = new Utente(username, password);
        UtentePostgresDAO UD = new UtentePostgresDAO();
        return UD.validateRegistration(U);
    }
    public static boolean deleteUtente(){
        Utente U = new Utente(idutenteStatic);
        UtentePostgresDAO UD = new UtentePostgresDAO();
        return UD.deleteUtente(U);
    }

    //PAGINA
    public static void getIdPaginaString(String titoloPaginaSelezionata, String autorePaginaSelezionata){
        Pagina P = new Pagina(titoloPaginaSelezionata.toUpperCase(), autorePaginaSelezionata);
        PaginaPostgresDAO PD = new PaginaPostgresDAO();
        PD.getIdPaginaString(P);
    }
    public static void getIdPaginaInt(String titoloPaginaSelezionata, int autorePaginaSelezionata){
        Pagina P = new Pagina(titoloPaginaSelezionata.toUpperCase(), autorePaginaSelezionata);
        PaginaPostgresDAO PD = new PaginaPostgresDAO();
        PD.getIdPaginaInt(P);
    }
    public static int getIdPaginaTit(String titolo){
        Pagina P = new Pagina(titolo.toUpperCase());
        PaginaPostgresDAO PD = new PaginaPostgresDAO();
        return PD.getIdPaginaTit(P);
    }
    public static void deletePagina(String titolo){
        Pagina P = new Pagina(titolo.toUpperCase());
        PaginaPostgresDAO PD = new PaginaPostgresDAO();
        PD.deletePagina(P);
    }
    public static String getTesto(){
        Pagina P = new Pagina(idpaginaStatic);
        PaginaPostgresDAO PD = new PaginaPostgresDAO();
        return PD.getTesto(P);
    }
    public static void getIdPaginaLink(String titolo){
        Pagina P = new Pagina(titolo);
        PaginaPostgresDAO PD = new PaginaPostgresDAO();
        PD.getIdPaginaLink(P);
    }
    public static boolean isLinkValid(String titolo){
        Pagina P = new Pagina(titolo.toUpperCase());
        PaginaPostgresDAO PD = new PaginaPostgresDAO();
        return PD.isLinkValid(P);
    }
    public static boolean isTitoloNotExist(String titolo){
        Pagina P = new Pagina(titolo.toUpperCase());
        PaginaPostgresDAO PD = new PaginaPostgresDAO();
        return PD.isTitoloNotExist(P);
    }
    public static void insertNewPage(String titolo){
        Pagina P = new Pagina(titolo.toUpperCase());
        PaginaPostgresDAO PD = new PaginaPostgresDAO();
        PD.insertNewPage(P);
    }

    //VISITA
    public static void insertView(){
        Visita V = new Visita(idpaginaStatic, idutenteStatic);
        VisitaPostgresDAO VD = new VisitaPostgresDAO();
        VD.insertView(V);
    }

    //FRASE
    public static void getIdFrase(String frase){
        Frase F = new Frase(frase);
        FrasePostgresDAO FD = new FrasePostgresDAO();
        FD.getIdFrase(F);
    }
    public static boolean identifySostituzione(){
        Frase F = new Frase(fraseStatic);
        FrasePostgresDAO FD = new FrasePostgresDAO();
        return FD.identifySostituzione(F);
    }
    public static String getFraseVisibile(){
        Frase F = new Frase(fraseStatic);
        FrasePostgresDAO FD = new FrasePostgresDAO();
        return FD.getFraseVisibile(F);
    }
    public static void updateFrase(boolean scelta){
        Frase F = new Frase(fraseStatic);
        FrasePostgresDAO FD = new FrasePostgresDAO();
        FD.updateFrase(F, scelta);
    }
    public static void insertNewText(String testo){
        Frase F = new Frase(testo);
        FrasePostgresDAO FD = new FrasePostgresDAO();
        FD.insertNewText(F);
    }
    public static void insertFraseProp(boolean sceltaSos, String proposta){
        Frase F = new Frase(proposta,posizioneStatic,idpaginaStatic,idutenteStatic,sceltaSos);
        FrasePostgresDAO FD = new FrasePostgresDAO();
        FD.insertFraseProp(F);
    }
    public static void insertFrasePropLink(boolean sceltaSos, String proposta, int link){
        Frase F = new Frase(proposta,posizioneStatic,idpaginaStatic,idutenteStatic,link,sceltaSos);
        FrasePostgresDAO FD = new FrasePostgresDAO();
        FD.insertFrasePropLink(F);
    }
    public static void setPosizione(String testo){
        Frase F = new Frase(testo);
        FrasePostgresDAO FD = new FrasePostgresDAO();
        FD.setPosizione(F);
    }
}
