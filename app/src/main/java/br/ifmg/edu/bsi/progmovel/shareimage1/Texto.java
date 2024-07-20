package br.ifmg.edu.bsi.progmovel.shareimage1;

import java.io.Serializable;

public class Texto implements Serializable {
    private String value;
    private int color;
    private float size;

    public Texto(String value, int color, float size){
        this.value = value;
        this.color = color;
        this.size = size;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }
}
