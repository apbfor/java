package com.apbfor;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Key> keys = new ArrayList<>();
        ArrayList<Door> doors = new ArrayList<>();
        doors.add(new Door("warehouse",123,"apbfor"));
        keys.add(new Key(123,"apbfor"));
        keys.add(new Key(1337,"apbfor"));
        int change = 10;

        while (change!=0){
            printMenu();
            change = in.nextInt();
            switch (change){
                case 1:{
                    printSubMenu();
                    int subChange = in.nextInt();
                    switch (subChange){
                        case 1:
                            printKeys(keys);
                            break;

                        case 2:{
                            String owner;
                            int number;
                            System.out.println("Enter your name");
                            owner = in.next();
                            System.out.println("Enter a number of your key");
                            number = in.nextInt();
                            Key newKey = new Key(number, owner);
                            keys.add(newKey);
                            break;
                        }
                        case 3:{
                            String owner;
                            int number;
                            System.out.println("Enter your name");
                            owner = in.next();
                            System.out.println("Enter a number of your key");
                            number = in.nextInt();
                            for (Key key : keys){
                                if(key.number==number){
                                    if(key.getOwner()!=owner){
                                        System.out.println("You aren't owner of this key");
                                        break;
                                    }
                                    else
                                        keys.remove(key);
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
                case 2:{
                    printSubMenu();
                    int subChange = in.nextInt();
                    switch (subChange){
                        case 1: {
                            int numberOfDoor = 0;
                            for (Door door : doors) {
                                System.out.println(numberOfDoor+". Door is "+door.name);
                                numberOfDoor++;
                            }
                            break;
                        }
                        case 2:{
                            String owner;
                            String name;
                            System.out.println("Enter your name");
                            owner = in.next();
                            System.out.println("Enter a name of your door");
                            name = in.next();
                            System.out.println("Enter key value for your door");
                            int keyVal = in.nextInt();
                            Door newDoor = new Door(name,keyVal,owner);
                            doors.add(newDoor);
                            break;
                        }
                        case 3:{
                            String owner;
                            String name;
                            int keyVal;
                            System.out.println("Enter your name");
                            owner = in.next();
                            System.out.println("Enter a name of your door");
                            name = in.next();
                            for (Door door : doors){
                                if(door.name==name){
                                    if(door.getOwner()!=owner){
                                        System.out.println("You aren't owner of this door");
                                        break;
                                    }
                                    else
                                        doors.remove(door);
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
                case 3:{
                    doorByKeys(doors,keys);
                    break;
                }

                case 4:{
                    keyForDoors(doors, keys);
                    break;
                }

                case 9:{
                    String name = in.next();
                    userKeys(keys,name);
                }


                case 0:
                    break;
                default:
                    System.out.println("unknown digit");
                    break;

            }
        }

    }

    static void userKeys(ArrayList<Key>keys,String owner){
        System.out.println("You have keys:");
        int keyNumber=0;
        for(Key key : keys){
            if(key.getOwner()==owner){
                System.out.println(keyNumber+". "+key.number+" key");
            }
        }
    }

    static void printKeys(ArrayList<Key>keys){
        int numberOfKey = 0;
        for (Key key : keys) {
            System.out.println(numberOfKey+". Key is "+key.number + "; owner is "+key.getOwner());
            numberOfKey++;
        }
    }
    static void keyForDoors(ArrayList<Door>doors, ArrayList<Key>keys){
        for (Key key : keys){
            for (Door door : doors){
                if(door.canBeOpenedBy(key))
                    System.out.println("Key "+key.number + " can open door " + door.name);
            }
        }
    }
    static void doorByKeys(ArrayList<Door>doors, ArrayList<Key>keys){
        for (Door door : doors){
            for (Key key : keys){
                if(door.canBeOpenedBy(key))
                    System.out.println("Door "+door.name + " can be opened with "+key.number+ " key.");
            }
        }
    }
    static void printMenu(){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("1. Edit keys list");
        System.out.println("2. Edit doors list");
        System.out.println("3. Make list of doors list that can be opened by one key");
        System.out.println("4. Make list of keys that can open doors");
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

}
