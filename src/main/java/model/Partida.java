package model;

import java.util.ArrayList;
import java.util.List;

public class Partida {

    private String idSessio;
    private boolean enMarxa;
    private boolean tancada;
    private List<Jugador> jugadors;
    private String titol;

    public Partida(String id) {
        this.idSessio = id; 
        this.jugadors = new ArrayList<Jugador>();
        this.tancada = false;
    }

 
    public String getIdSessio() {
        return idSessio;
    }

    public void setIdSessio(String idSessio) {
        this.idSessio = idSessio;
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
        System.out.println("Jugadors nets:" + jugadors);
        //Posam tots els jugadors que ens passen per actualitzar
        for(Jugador j : jugadors){
            System.out.println("Insertant jugador:" + j );
            this.jugadors.add(j);
        }
        System.out.println("Jugadors insertats: " + this.jugadors);
    }



}
