package com.apbfor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.sqrt;

public class Point implements IDrawable {
    private double x, y;
    private List<Point> storage = new ArrayList<>();

    void makeList(int minCount, int maxCount){
        Random random = new Random();
        int count = random.nextInt(maxCount - minCount);
        count += minCount;
        int minValue = -10;
        int maxValue = 10;
        for (int i=0; i<count; i++){
            double valueX = random.nextInt(maxValue - minValue);
            double valueY = random.nextInt(maxValue - minValue);
            valueX += minValue;
            valueY += minValue;
            storage.add(new Point(valueX, valueY));
        }
    }

    List<Point> getStorage() {
        return  storage;
    }

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
        return x + " " + y;
    }

    public String giveDistance(){
        return "This is point. X = " + x  + "; Y = " + y + "; distance from (0,0) = "+ sqrt(x*x+y*y) ;
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
