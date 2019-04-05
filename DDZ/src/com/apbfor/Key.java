package com.apbfor;

public class Key {
    private
    String owner="root";

    public
    int number;
    String getOwner(){
        return owner;
    }
    void setOwner(String owner){
        this.owner = owner;
    }
    Key(int number){
        this.number = number;
    }
    Key(){}
    Key(int number, String owner){
        this.number = number;
        this.owner = owner;
    }
}
