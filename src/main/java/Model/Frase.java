package Model;
import java.sql.Date;
import java.sql.Time;

public class Frase {
    public int idfrase;
    public String frase;
    public int posizione;
    public boolean visibile;
    public boolean accettata;
    public Date data;
    public Time ora;
    public int idpagina;
    public int idutente;
    public int idlink;
    public int idautore;
    public boolean sostituzione;

    public Frase(int idfrase, String frase, int posizione, boolean visibile, boolean accettata, Date data, Time ora, int idpagina, int idutente, int idlink, int idautore, boolean sostituzione) {
        this.idfrase =idfrase;
        this.frase =frase;
        this.posizione =posizione;
        this.visibile=visibile;
        this.accettata=accettata;
        this.data=data;
        this.ora=ora;
        this.idpagina=idpagina;
        this.idutente=idutente;
        this.idlink=idlink;
        this.idautore=idautore;
        this.sostituzione=sostituzione;
    }

    public Frase(String frase, boolean visibile, boolean accettata, boolean sostituzione) {
        this.frase =frase;
        this.visibile=visibile;
        this.accettata=accettata;
        this.sostituzione=sostituzione;
    }

    public Frase(String frase){
        this.frase =frase;
    }
    public Frase(String frase, int idfrase){
        this.frase =frase;
        this.idfrase= idfrase;
    }
    public Frase(int posizione){
        this.posizione =posizione;

    }

    public Frase(String frase, int posizione, int idpagina, int idutente, boolean sostituzione) {
        this.frase = frase;
        this.posizione = posizione;
        this.idpagina = idpagina;
        this.idutente = idutente;
        this.sostituzione = sostituzione;
    }

    public Frase(String frase, int posizione, int idpagina, int idutente, int idlink, boolean sostituzione) {
        this.frase = frase;
        this.posizione = posizione;
        this.idpagina = idpagina;
        this.idutente = idutente;
        this.idlink=idlink;
        this.sostituzione = sostituzione;
    }

    public boolean getSostituzione() {
        return sostituzione;
    }
    public int getIdfrase() {
        return idfrase;
    }

    public String getFrase() {
        return frase;
    }

    public int getPosizione() {
        return posizione;
    }

    public boolean getVisibile() {
        return visibile;
    }

    public boolean getAccettata() {
        return accettata;
    }

    public Date getData() {
        return data;
    }

    public Time getOra() {
        return ora;
    }

    public int getIdpagina() {
        return idpagina;
    }

    public int getIdutente() {
        return idutente;
    }

    public int getIdlink() {
        return idlink;
    }

    public int getIdautore() {
        return idautore;
    }
    public void setSostituzione(boolean sostituzione){
        this.sostituzione=sostituzione;
    }
    public void setIdfrase(int idfrase) {
        this.idfrase = idfrase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public void setPosizione(int posizione) {
        this.posizione = posizione;
    }

    public void setVisibile(boolean visibile) {
        this.visibile = visibile;
    }

    public void setAccettata(boolean accettata) {
        this.accettata = accettata;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setOra(Time ora) {
        this.ora = ora;
    }

    public void setIdpagina(int idpagina) {
        this.idpagina = idpagina;
    }

    public void setIdutente(int idutente) {
        this.idutente = idutente;
    }

    public void setIdlink(int idlink) {
        this.idlink = idlink;
    }

    public void setIdautore(int idautore) {
        this.idautore = idautore;
    }

    public static boolean isTextAreaValid(String stringaText) {
        // Controlla se la stringa è null o vuota
        if (stringaText == null || stringaText.trim().isEmpty()) {
            return false;
        }
        // Controlla se la stringa contiene almeno un carattere alfabetico
        return stringaText.matches(".*[a-zA-Z].*");
    }

}
