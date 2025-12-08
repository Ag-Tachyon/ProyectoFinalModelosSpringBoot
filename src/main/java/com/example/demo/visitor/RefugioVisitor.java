package com.example.demo.visitor;

import com.example.demo.model.*;

public interface RefugioVisitor {

    public String visitar(RefugioPerros refugioPerros);
    public String visitar(RefugioGatos refugioGatos);

}
