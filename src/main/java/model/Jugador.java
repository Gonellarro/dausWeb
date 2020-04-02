package model;

public class Jugador {
    private String nom;
    private String avatar;
    private int hashJugador;
    private boolean creador;
    private int valorDau;
    
    public Jugador(){
        this.avatar = "default";
        this.hashJugador = 0;
        this.creador = false;
        this.valorDau = 0;
    }

    public String getNom() {
        return nom;
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

    public int getHashJugador() {
        return hashJugador;
    }

    public void setHashJugador(int hashJugador) {
        this.hashJugador = hashJugador;
    }

    public boolean isCreador() {
        return creador;
    }

    public void setCreador(boolean creador) {
        this.creador = creador;
    }

    public int getValorDau() {
        return valorDau;
    }

    public void setValorDau(int valorDau) {
        this.valorDau = valorDau;
    }

    @Override
    public String toString() {
        return "Jugador{" + "nom=" + nom + ", avatar=" + avatar + ", hashJugador=" + hashJugador + ", creador=" + creador + ", valorDau=" + valorDau + '}';
    }
    
    
}
