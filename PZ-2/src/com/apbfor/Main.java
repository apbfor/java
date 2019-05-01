package com.apbfor;


public class Main {

    public static void main(String[] args) {

        //TASK-1
        Point a = new Point(3,13);
        System.out.println(a.ToString());
        Point b = new Point(3,13);
        System.out.println(a.equals(b));

        System.out.println();

        //TASK-2
        Circle circ1 = new Circle(a,8);
        Circle circ2 = new Circle(b,8);
        System.out.println(circ1.ToString());
        System.out.println(circ1.equals(circ2));


        //TASK-3
        Point[] points = new Point[]{new Point(1,2), new Point(1.4,2.1),new Point(-2.1,0.5)};
        for (int i=0; i<3;i++) {
            System.out.println(points[i].ToString());
        }

        //TASK-4
        Point[] cords = new Point[]{new Point(2,0), new Point(1.6,2), new Point(-1.2,-2)};
        Circle[] circles = new Circle[]{new Circle(cords[0],1),
                new Circle(cords[1],2),
                new Circle(cords[2], 2.5)};




        System.out.println("Combo!");


        IDrawable[]arr3 = new IDrawable[points.length+circles.length];
        for(int i=0; i<points.length; i++)
            arr3[i]=points[i];
        for(int i=0; i<circles.length; i++)
            arr3[i+points.length]=circles[i];

        for(int i=0; i<arr3.length; i++)
            arr3[i].draw();

    }

    interface IDrawable{
        void draw();
    }



}
