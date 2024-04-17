package Model;

public class Visita {
    public int idutente;
    public int idpagina;

    public Visita( int idpagina, int idutente){
        this.idpagina=idpagina;
        this.idutente=idutente;
    }

    public int getIdutente() {
        return idutente;
    }

    public void setIdutente(int idutente) {
        this.idutente = idutente;
    }

    public int getIdpagina() {
        return idpagina;
    }

    public void setIdpagina(int idpagina) {
        this.idpagina = idpagina;
    }
}
