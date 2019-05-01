package com.apbfor;

public class Point implements IDrawable {
    private double x, y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    Point() {
        x = y = 0;
    }

    public void Set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "x = " + x + "; y = " + y;
    }

    @Override
    public boolean equals(Object second) {
        if (!second.getClass().isInstance(new Point())||second==null)
            return false;
        return this.x == ((Point)second).x && this.y == ((Point)second).y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void draw() {
        System.out.println("This is Point, x = " + x + "; y = " + y);
    }


    public int compareTox(Point o) {
        if (this.getX() < o.getX())
            return -1;
        else if (this.getX() > o.getX())
            return 1;
        return 0;
    }
}
