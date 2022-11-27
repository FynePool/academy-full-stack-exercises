package com.riccardorocco.Disegno;

public class Rettangolo extends Figura {
    private double base, altezza;

    public Rettangolo(double base, double altezza){
        this.altezza=altezza;
        this.base=base;
    }

    @Override
    public double area() {
        return base*altezza;
    }

    @Override
    public String getNome() {
        return "rettangolo";
    }

}
