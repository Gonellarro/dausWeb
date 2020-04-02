package model;

import java.util.ArrayList;
import java.util.List;

public class Partida {

    private int hashPartida;
    private boolean enMarxa;
    private boolean tancada;
    private List<Jugador> jugadors;
    private String titol;

    public Partida(int id) {
        this.hashPartida = id; 
        this.jugadors = new ArrayList<Jugador>();
        this.tancada = false;
    }

 
    public int getHashPartida() {
        return hashPartida;
    }

    public void setHashPartida(int hashPartida) {
        this.hashPartida = hashPartida;
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


    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }
    
    public boolean isTancada() {
        return tancada;
    }

    public void setTancada(boolean tancada) {
        this.tancada = tancada;
    }
    
    public void afegeixJugador(Jugador jugador) {
        this.jugadors.add(jugador);
    }
    
    public void actualitzaJugadors(List<Jugador> jugadors){
        //Netejam la llista
        this.jugadors.clear();
        //Posam tots els jugadors que ens passen per actualitzar
        for(Jugador j : jugadors){
            this.jugadors.add(j);
        }
    }
    
    public void actualitzaDadesJugador(Jugador jugadorExtern){
        int index = this.jugadors.indexOf(jugadorExtern);
        System.out.println("Index: " + index);
        System.out.println("Jugador: "+  jugadorExtern);
    }



}
