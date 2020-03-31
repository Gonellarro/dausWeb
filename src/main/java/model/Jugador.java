package model;

public class Jugador {
    private String nom;
    private String avatar;
    private String idSessio;
    private boolean creador;
    private int valorDau;
    
    public Jugador(){
        this.avatar = "default";
        this.idSessio = "";
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

    public String getIdSessio() {
        return idSessio;
    }

    public void setIdSessio(String idSessio) {
        this.idSessio = idSessio;
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
    
    
}
