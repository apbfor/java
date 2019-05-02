package com.apbfor;

import javax.annotation.processing.SupportedSourceVersion;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<Key> keys = new ArrayList<>();
        ArrayList<Door> doors = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        //Здесь будет импорт данных из файла

//        users.add(new User("Vasya", "vasya"));
//        users.add(new User("Petya", "petya"));
//        users.add(new User("Igor", "igor"));
//        users.add(new User("root","root"));
//
//        keys.add(new Key(156, users.get(0)));
//        keys.add(new Key(157, users.get(1)));
//        keys.add(new Key(158, users.get(2)));
//        keys.add(new Key(159, users.get(2)));
//
//        doors.add(new Door("VasyaRoom", 156));
//        doors.add(new Door("PetyaRoom", 157));
//        doors.add(new Door("IgorRoom", 158));
//        doors.add(new Door("IgorRoom2", 159));

        File doorr = new File("doors.dat");
        File keyy = new File("keys.dat");
        File userr = new File("users.dat");

        if(doorr.exists()&&keyy.exists()&&userr.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(doorr))) {
                doors = ((ArrayList<Door>) ois.readObject());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(keyy))) {
                keys = ((ArrayList<Key>) ois.readObject());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(userr))) {
                users = ((ArrayList<User>) ois.readObject());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }else {
            System.out.println("Data was lost, can't work now");
        }
        String name;
        String password;
        System.out.print("Enter your name : ");
        name = in.next();
        System.out.print("Enter your password : ");
        password = in.next();
        User newUser = new User(name, password);

        start : for (User user : users) {
            if (newUser.equals(user) && user.isRoot()) {
                System.out.println("congratulations, you are root");
                int change = 10;
                while (change != 0) {
                    printAdminMenu();
                    change = in.nextInt();
                    switch (change) {
                        case 1: {
                            printSubMenu();
                            int subChange = in.nextInt();
                            switch (subChange) {
                                case 1:
                                    printKeys(keys);
                                    break;

                                case 2: {
                                    boolean userExists = false;
                                    String keyOwner;
                                    int number;
                                    System.out.println("Enter name of new key owner");
                                    keyOwner = in.next();
                                    System.out.println("Enter a number of key");
                                    number = in.nextInt();
                                    Key newKey;
                                    for(User user1 : users){
                                        if(user1.name.equals(keyOwner)) {
                                            System.out.println("User already have been added, now you added a key");
                                            newKey = new Key(number,user1);
                                            keys.add(newKey);
                                            break;
                                        }
                                        else if (user1.equals(users.get(users.size() - 1))) {
                                            System.out.println("User does not exists, try again");
                                            break;
                                        }
                                    }

                                    break;
                                }
                                case 3: {
                                    int number;
                                    System.out.println("Enter a number of key to delete");
                                    number = in.nextInt();
                                    for (Key key : keys) {
                                        if (key.number == number) {
                                            keys.remove(key);
                                            break;
                                        }
                                    }
                                }
                                break;
                                default:
                                    System.out.println("unknown digit");
                                    break;
                            }

                            break;
                        }


                        case 2: {
                            printSubMenu();
                            int subChange = in.nextInt();
                            switch (subChange) {
                                case 1: {
                                    int numberOfDoor = 0;
                                    for (Door door : doors) {
                                        System.out.println(numberOfDoor + ". Door is " + door.name);
                                        numberOfDoor++;
                                    }
                                    break;
                                }
                                case 2: {
                                    System.out.println("Enter a name of door");
                                    name = in.next();
                                    System.out.println("Enter key value for door");
                                    int keyVal = in.nextInt();
                                    Door newDoor = new Door(name, keyVal);
                                    doors.add(newDoor);
                                    break;
                                }
                                case 3: {
                                    String nameOfDoor;
                                    System.out.println("Enter a name of door");
                                    nameOfDoor = in.next();
                                    for (Door door : doors) {
                                        if (door.name.equals(nameOfDoor)) {
                                            doors.remove(door);
                                            break;
                                        }
                                    }
                                    break;
                                }

                                default:
                                    System.out.println("unknown digit");
                                    break;
                            }

                            break;
                        }
                        case 3: {
                            doorByKeys(doors, keys);
                            break;
                        }

                        case 4: {
                            keyForDoors(doors, keys);
                            break;
                        }

                        case 5:{
                            printSubMenu();
                            int subChange = in.nextInt();
                            switch (subChange){
                                case 1:{
                                    int numberOfUser = 0;
                                    for (User user1 : users) {
                                        System.out.println(numberOfUser + ". User is " + user1.name);
                                        numberOfUser++;
                                    }
                                    break;
                                }

                                case 2: {
                                    System.out.println("Enter a name of user");
                                    name = in.next();
                                    System.out.println("Enter a password for new user");
                                    password = in.next();
                                    User newUser1 = new User(name, password);
                                    users.add(newUser1);
                                    break;
                                }
                                case 3: {
                                    String nameOfUser;
                                    System.out.println("Enter a name of user");
                                    nameOfUser = in.next();
                                    for (User user1 : users) {
                                        if (user1.name.equals(nameOfUser)) {
                                            System.out.println("user found and was deleted");
                                            users.remove(user1);
                                            break;
                                        }
                                    }
                                    break;
                                }
                            }
                            break;
                        }




                        case 0:
                            break;
                        default:
                            System.out.println("unknown digit");
                            break;

                    }
                }
                break start;

            }
        else if (newUser.equals(user)) {
                System.out.println("You have been logged as " + user.name);
                System.out.print("You have key(s) : ");
                for (Key key : keys) {
                    for (Door door : doors) {
                        if (key.owner.equals(newUser)) {
                            if (door.canBeOpenedBy(key.number)) {
                                System.out.print(key.number + " from " + door.name + "; ");
                            }
                        }
                    }

                }
                break;
            }

         else if (user.equals(users.get(users.size() - 1)))
                System.out.println("Incorrect username/password");


        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.dat"))){
            oos.writeObject(users);
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("doors.dat"))){
            oos.writeObject(doors);
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("keys.dat"))){
            oos.writeObject(keys);
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

}


    public static void printAdminMenu(){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("1. Edit keys list");
        System.out.println("2. Edit doors list");
        System.out.println("3. Make list of doors list that can be opened by one key");
        System.out.println("4. Make list of keys that can open doors");
        System.out.println("5. Edit users list");
        System.out.println("0. Exit");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    static void  printSubMenu(){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("1. Print list");
        System.out.println("2. Add an item in list");
        System.out.println("3. Remove an item from list");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
    static void userKeys(ArrayList<Key>keys,String owner){
        Scanner in = new Scanner(System.in);
        System.out.println("You have keys:");
        int keyNumber=0;
        for(Key key : keys){
            if(key.owner.equals(owner)){
                System.out.println(keyNumber+". "+key.number+" key");
                keyNumber++;
            }
        }
        System.out.println("Take a key >> ");
        int takeKey = in.nextInt();
        System.out.println(keys.get(takeKey).number);
    }

    static void printKeys(ArrayList<Key>keys){
        int numberOfKey = 0;
        for (Key key : keys) {
            System.out.println(numberOfKey+". Key is "+key.number + "; owner is "+key.owner.name);
            numberOfKey++;
        }
    }
    static void keyForDoors(ArrayList<Door>doors, ArrayList<Key>keys){
        for (Key key : keys){
            for (Door door : doors){
                if(door.canBeOpenedBy(key.number))
                    System.out.println("Key "+key.number + " can open door " + door.name);
            }
        }
    }
    static void doorByKeys(ArrayList<Door>doors, ArrayList<Key>keys){
        for (Door door : doors){
            for (Key key : keys){
                if(door.canBeOpenedBy(key.number))
                    System.out.println("Door "+door.name + " can be opened with "+key.number+ " key.");
            }
        }
    }

}
