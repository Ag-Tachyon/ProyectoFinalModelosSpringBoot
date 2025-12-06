package com.example.demo.iterator;

import com.example.demo.model.Mascota;

public interface IteradorMascota {
    public Boolean hasNext();
    public Mascota next();
}
