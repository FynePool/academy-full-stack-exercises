package com.riccardorocco.Disegno;

public class Cerchio extends Figura{

    private double raggio;

    public Cerchio(double raggio){
        this.raggio=raggio;
    }

    @Override
    public double area() {
        return raggio*raggio*Math.PI;
    }

    @Override
    public String getNome() {
        return "Cerchio";
    }

}
