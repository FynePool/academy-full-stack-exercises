package com.riccardorocco.Disegno;

public abstract class Figura {
    public abstract double area();
    public abstract String getNome();
    @Override
    public String toString() {
        return "L'area della figura " + getNome().toLowerCase() + " è: " + area();
    }
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o instanceof Figura) {
            return this.area() == ((Figura)o).area();
        }
        return false;
    }
}