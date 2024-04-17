package Model;

import java.sql.Time;
import java.sql.Date;

public class Pagina {
    public  int idpagina;
    public  String Titolo;
    public  int numVisite;
    public  Date Data;
    public  Time Ora;
    public  int idautore;
    public  String email;


    public Pagina(String Titolo, String email, int idpagina){
        this.Titolo =Titolo;
        this.email =email;
        this.idpagina = idpagina;
    }

    public Pagina(int idpagina) {
        this.idpagina = idpagina;
    }

    public Pagina(String Titolo, int idautore, int idpagina){
        this.Titolo =Titolo;
        this.idautore =idautore;
        this.idpagina = idpagina;
    }

    public Pagina(String Titolo, String email){
        this.Titolo =Titolo;
        this.email =email;
    }

    public Pagina(String Titolo, int idautore){
        this.Titolo =Titolo;
        this.idautore =idautore;
    }

    public Pagina(int idpagina, String Titolo){
        this.Titolo =Titolo;
        this.idpagina =idpagina;
    }

    public Pagina(int numVisite, String Titolo, String email){
        this.numVisite=numVisite;
        this.Titolo =Titolo;
        this.email =email;
    }

    public Pagina(String Titolo){
        this.Titolo =Titolo;
    }

    public Pagina(String Titolo,Date Data, Time Ora){
        this.Titolo =Titolo;
        this.Data=Data;
        this.Ora=Ora;
    }

    public int getIdpagina() {
        return idpagina;
    }

    public void setIdpagina(int idpagina) {
        this.idpagina = idpagina;
    }

    public String getTitolo() {
        return Titolo;
    }

    public void setTitolo(String titolo) {
        Titolo = titolo;
    }

    public int getNumVisite() {
        return numVisite;
    }

    public void setNumVisite(int numVisite) {
        this.numVisite = numVisite;
    }

    public Date getData() {
        return Data;
    }

    public void setData(Date data) {
        Data = data;
    }

    public Time getOra() {
        return Ora;
    }

    public void setOra(Time ora) {
        Ora = ora;
    }

    public int getIdautore() {
        return idautore;
    }

    public void setIdautore(int idautore) {
        this.idautore = idautore;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return Titolo; // Restituisce il titolo della pagina
    }
}
