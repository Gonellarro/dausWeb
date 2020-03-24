package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Partida {

    private static int totalPartides = 0;
    private int punts;
    private String idSessio;
    private boolean enMarxa;
    private ArrayList<Jugador> jugadors;
    private int maxJugadors;

    public Partida(String id) {
        this.punts = 0;
        this.idSessio = id;
        totalPartides++;
        this.jugadors = new ArrayList<Jugador>();
        this.maxJugadors = 99;
    }

    public int getPunts() {
        return punts;
    }

    public void setPunts(int punts) {
        this.punts = punts;
    }

    public String getIdSessio() {
        return idSessio;
    }

    public void setIdSessio(String idSessio) {
        this.idSessio = idSessio;
    }

    public static int getTotalPartides() {
        return totalPartides;
    }

    public static void setTotalPartides(int aTotalPartides) {
        totalPartides = aTotalPartides;
    }

    public boolean isEnMarxa() {
        return enMarxa;
    }

    public void setEnMarxa(boolean enMarxa) {
        this.enMarxa = enMarxa;
    }

    public ArrayList<Jugador> getJugadors() {
        return jugadors;
    }

    public void setJugadors(ArrayList<Jugador> jugadors) {
        this.jugadors = jugadors;
    }

    public void afegeixJugador(Jugador jugador) {
        this.jugadors.add(jugador);
    }

}
