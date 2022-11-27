package com.javacourse.springapp.model;

public class Tentativo {
    private int value;
    private int result;

    public Tentativo(int value, int secret) {
        this.value = value;
        this.result = Integer.compare(value, secret);
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getResult() {
        return this.result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String toString() {
        if (result == 0) {
            return "Bravo, hai indovinato";
        } else if (result < 0) {
            return String.format("Il numero %d è troppo piccolo.", value);
        } else {
            return String.format("Il numero %d è troppo grande.", value);
        }
    }

    public String getClassName() {
        if (result == 0) {
            return "correct";
        } else if (result < 0) {
            return "too-low";
        } else {
            return "too-high";
        }
    }
}