package model;

public class Dau {
    private int valor;
    private int maxValor;
    
    public Dau(){
        //Inicialitzam els valors per defecte del dau
        this.valor = 1;
        this.maxValor = 6;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getMaxValor() {
        return maxValor;
    }

    public void setMaxValor(int maxValor) {
        this.maxValor = maxValor;
    }
    
    public int llancar(){
        return this.valor =  (int) Math.floor(Math.random()*this.maxValor+1);
    }
    
}
