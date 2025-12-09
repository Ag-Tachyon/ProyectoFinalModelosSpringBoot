package com.example.demo.controller;

public class FiltroRequest {
    private String size;
    private int edadMinima;

    // Getters y Setters
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getEdadMinima() {
        return edadMinima;
    }

    public void setEdadMinima(int edadMinima) {
        this.edadMinima = edadMinima;
    }

    @Override
    public String toString() {
        return "FiltroRequest{" +
                "size='" + size + '\'' +
                ", edadMinima=" + edadMinima +
                '}';
    }
}