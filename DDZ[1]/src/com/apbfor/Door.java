package com.apbfor;

import java.io.Serializable;

public class Door implements Serializable {
    String name;
    int keyNumber;

    boolean canBeOpenedBy(int keyNumber){
        if (this.keyNumber == keyNumber)
            return true;
        return false;
    }

    Door(){
        name = "Main";
        keyNumber = 123;
    }

    Door(String name, int keyNumber){
        this.name = name;
        this.keyNumber = keyNumber;
    }
}
