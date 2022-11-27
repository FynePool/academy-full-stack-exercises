package com.riccardorocco.Disegno;

public class Disegno {
    public static void main(String[] args) {
        Figura[] foglio = { new Rettangolo(2, 3), new Quadrato(5),
                            new Triangolo(4, 5), new Cerchio(6) };
        for(Figura f: foglio) {
            System.out.println(f);
        }
    }
}