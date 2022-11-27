package com.riccardorocco;

public class TwoVariables {
    
    private int a;
    private int b;

    public TwoVariables(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA(){ return this.a; }

    public int getB(){ return this.b; }

    public void swap() {
        a = a+b;
        b = a-b;
        a = a-b;
    }
}