package model;

import java.util.ArrayList;
import java.util.List;

public class Partida {

    private static int totalPartides = 0;
    private int punts;
    private String idSessio;
    private boolean enMarxa;
    private List<Jugador> jugadors;
    private int maxJugadors;
    private String titol;

    public Partida(String id) {
        this.punts = 0;
        this.idSessio = id; 
        totalPartides++;
        this.jugadors = new ArrayList<Jugador>();
        this.maxJugadors = 99;
    }

    @Override
    public String toString() {
        return "Partida{" + "punts=" + punts + ", idSessio=" + idSessio + ", enMarxa=" + enMarxa + ", jugadors=" + jugadors + ", maxJugadors=" + maxJugadors + ", titol=" + titol + '}';
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

    public List<Jugador> getJugadors() {
        return jugadors;
    }

    public void setJugadors(List<Jugador> jugadors) {
        this.jugadors = jugadors;
    }

    public int getMaxJugadors() {
        return maxJugadors;
    }

    public void setMaxJugadors(int maxJugadors) {
        this.maxJugadors = maxJugadors;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public void afegeixJugador(Jugador jugador) {
        this.jugadors.add(jugador);
    }
    
    public void actualitzaJugadors(List<Jugador> jugadors){
        //Netejam la llista
        this.jugadors.clear();
        System.out.println("Jugadors nets:" + jugadors);
        //Posam tots els jugadors que ens passen per actualitzar
        for(Jugador j : jugadors){
            System.out.println("Insertant jugador:" + j );
            this.jugadors.add(j);
        }
        System.out.println("Jugadors insertats: " + this.jugadors);
    }

}
