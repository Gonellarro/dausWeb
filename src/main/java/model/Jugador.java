package model;

public class Jugador {
    private String nom;
    private String avatar;
    private int punts;
    private int partidesJugades;
    private String idSessio;
    private int valorDau;
    private boolean creador;
    
    public Jugador(){
        this.partidesJugades = 0;
        this.punts = 0;
        this.avatar = "default";
        this.idSessio = "";
        this.valorDau = 1;
        this.creador = false;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return "Jugador{" + "nom=" + nom + ", avatar=" + avatar + ", punts=" + punts + ", partidesJugades=" + partidesJugades + ", idSessio=" + idSessio + ", valorDau=" + valorDau + ", creador=" + creador + '}';
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getPunts() {
        return punts;
    }

    public void setPunts(int punts) {
        this.punts = punts;
    }

    public int getPartidesJugades() {
        return partidesJugades;
    }

    public void setPartidesJugades(int partidesJugades) {
        this.partidesJugades = partidesJugades;
    }

    public String getIdSessio() {
        return idSessio;
    }

    public void setIdSessio(String idSessio) {
        this.idSessio = idSessio;
    }

    public int getValorDau() {
        return valorDau;
    }

    public void setValorDau(int valorDau) {
        this.valorDau = valorDau;
    }

    public boolean isCreador() {
        return creador;
    }

    public void setCreador(boolean creador) {
        this.creador = creador;
    }
    
    
}
