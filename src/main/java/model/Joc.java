package model;

public class Joc {

    
    private Dau dau;
    private Partida partida;

    public Joc() {
        this.dau = new Dau();
    }

    public Dau getDau() {
        return dau;
    }

    public void setDau(Dau dau) {
        this.dau = dau;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }
  
    public void llancarDaus(Jugador jugador) {
        jugador.setValorDau(this.dau.llancar());
    }
}
