package Dyv;

public class Ladrillo {
    
    private int alturaMax;
    private boolean roto;

    public Ladrillo() {
        this.alturaMax = 0;
        this.roto = false;
    }
    
    public Ladrillo(int alturaMax){
        this.alturaMax = alturaMax;
    }

    public int getAlturaMax() {
        return alturaMax;
    }

    public void setAlturaMax(int alturaMax) {
        this.alturaMax = alturaMax;
    }

    public boolean isRoto() {
        return roto;
    }

    public void setRoto(boolean roto) {
        this.roto = roto;
    }
    
}
