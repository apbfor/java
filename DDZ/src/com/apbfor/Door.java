package com.apbfor;

public class Door {
    private
    String owner="root";

    public
    String name = "silver door";
    int keyValue = 0; //number of key who can open the door

    String getOwner(){
        return owner;
    }
    void setOwner(String owner){
        this.owner = owner;
    }

    Door(){}
    Door(String name, int keyValue){
        this.keyValue = keyValue;
        this.name = name;
    }
    Door(String name){
        this.name = name;
    }
    Door(String name, int keyValue, String owner){
        this.name = name;
        this.keyValue = keyValue;
        this.owner = owner;
    }


    boolean canBeOpenedBy(Key key){
        if(key.number == keyValue) {
            return true;
        }
        return false;
    }
}
