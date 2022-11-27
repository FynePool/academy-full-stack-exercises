package com.riccardorocco.Disegno;

public class Triangolo extends Figura {
    private double base, altezza;

    public Triangolo(double base, int altezza){
        this.altezza=altezza;
        this.base=base;
    }

    @Override
    public double area() {
        return base*altezza;
    }

    @Override
    public String getNome() {
        return "triangolo";
    }

}
