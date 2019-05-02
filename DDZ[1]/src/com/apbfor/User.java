package com.apbfor;

import java.io.Serializable;

public class User implements Serializable {
    private String password;
    String name;

    User(){
        name = "user";
        this.setPassword("user");
    }
    User(String name, String pass){
        this.name = name;
        this.setPassword(pass);
    }

    Boolean isRoot(){
        if(this.name.equals("root"))
            return true;
        return false;
    }


    void setPassword(String pass){
        this.password = cryptPass(pass);
    }

    String cryptPass(String pass){ //тут может быть нормальное шифрование
        String encmsg = "";
        String key = "41";
        int keylen = key.length();
        int msglen = pass.length();
        int j;
        j = 0;
        for(int i = 0; i < msglen; i++) {
            encmsg = encmsg + (char) (pass.charAt(i) ^ key.charAt(j));
            j++;
            if(j==keylen) {
                j=0;
            }
        }
        //System.out.println("Encoded message: "+encmsg); //пригодится для добавления паролей в базу
        return encmsg;
    }

    @Override
    public boolean equals(Object obj) {
        if( ((User)obj).name.equals(((User)this).name) && ((User)this).password.equals(((User)obj).password))
            return true;
        return false;
    }


}
