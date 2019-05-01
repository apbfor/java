package com.apbfor;


public class Circle implements IDrawable {

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

//    @Override
//    public String toString(){
//        return "x = "+  center.getX()+"; y = "+center.getY()+"; radius = "+radius+"; length = "+length;
//    }

    @Override
    public String toString(){
        return center.getX()+" "+ center.getY() +" "+ radius;
    }

    @Override
    public boolean equals(Object second) {
        if (!second.getClass().isInstance(new Circle())||second==null)
            return false;
        return this.radius==((Circle)second).radius&&center.equals(((Circle)second).center);
    }

    public void draw(){
     System.out.println("This is circle, center cords is: x = "+ center.getX()+"; y = "+ center.getY()
     +"; radius = "+radius+"; length = "+length);
    }

    public double getX(){
        return center.getX();
    }

    
    public double getY(){
        return center.getY();
    }

    public double getRadius() {
        return radius;
    }
    public int compareTox(Circle o){
        if (this.getX()<o.getX())
            return -1;
        else if (this.getX()>o.getX())
            return 1;
        return 0;
    }
}
