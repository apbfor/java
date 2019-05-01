package com.apbfor;

import com.sun.source.tree.IdentifierTree;

public class Circle implements Main.IDrawable {

    private Point center = new Point();
    private double radius;
    private double length;

    Circle(Point center, double radius){
        this.center = center;
        this.radius = radius;
        this.length = 2*3.14*this.radius;
    }

    Circle(){
        this.center = new Point();
        this.radius = 5;
        this.length = 2*3.14*radius;
    }

    String ToString(){
        return "x = "+center.getX()+"; y = "+center.getY()+"; radius = "+radius+"; length = "+length;
    }

    boolean equals(Circle second){
        return this.radius==second.radius&&center.equals(second.center);
    }

    public void draw(){
     System.out.println("This is circle, center cords is: x = "+center.getX()+"; y = "+center.getY()
     +"; radius = "+radius+"; length = "+length);
    }

    public double GetX(){
        return center.getX();
    }

    public double GetY(){
        return center.getY();
    }

    public double GetRadius() {
        return radius;
    }
    public int compareTox(Circle o){
        if (this.GetX()<o.GetX())
            return -1;
        else if (this.GetX()>o.GetX())
            return 1;
        return 0;
    }
}
