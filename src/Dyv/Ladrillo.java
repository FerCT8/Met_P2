package Dyv;

public class Ladrillo {
    
    private int resistencia;
    private boolean roto;

    public Ladrillo() {
        this.resistencia = 0;
        this.roto = false;
    }
    
    public Ladrillo(int altura){
        this.resistencia = altura;
    }

    public int getAlturaMax() {
        return resistencia;
    }

    public void setAlturaMax(int alturaMax) {
        this.resistencia = alturaMax;
    }

    public boolean isRoto() {
        return roto;
    }

    public void setRoto(boolean roto) {
        this.roto = roto;
    }
    
}
