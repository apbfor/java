package com.apbfor;

public class Key {
    int number;
    User owner;

    void SetOwner(User owner){
        this.owner = owner;
    }

    Key(){
        number = 123;
        owner = new User();
    }

    Key(int number, User owner){
        this.number = number;
        this.owner = owner;
    }

}
