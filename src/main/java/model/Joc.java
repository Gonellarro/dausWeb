package model;

public class Joc {

    
    private Dau dau;
    private Jugador torn;
    private Partida partida;

    public Joc() {
        this.dau = new Dau();
        this.torn = null;
        this.partida = null;
    }

    public Dau getDau() {
        return dau;
    }

    public void setDau(Dau dau) {
        this.dau = dau;
    }

    public Jugador getTorn() {
        return torn;
    }

    public void setTorn(Jugador torn) {
        this.torn = torn;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public void tirarDau(Jugador jugador) {
        jugador.setValorDau(this.dau.llancar());
    }
}
