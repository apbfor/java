package com.apbfor;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static java.lang.Thread.sleep;

public class Main {

    static Producer mProd;
    static Consumer mCons;

    public static void main(String[] args) {
//        Point a = new Point();
//        a.makeList(10, 20);
//        List<Point> points = a.getStorage();
//        for (Point point : points) {
//            System.out.println(point);
//        }

        BlockingQueue<Point> drop = new ArrayBlockingQueue(1, true);

        mProd = new Producer(drop);
        mCons = new Consumer(drop);
        mProd.start();
        mCons.start();
        System.out.println("Enter 0 if you want to stop process");
        Scanner in = new Scanner(System.in);
        int change = 5;
        change = in.nextInt();
        if(change==0){
            mProd.interrupt();
            //mCons.interrupt();
        }

        System.out.println("Program ended.");
    }

}
    class Producer extends Thread implements Runnable {
        private BlockingQueue<Point> drop;
        List<Point> points = new ArrayList<>();

        public Producer(BlockingQueue<Point> d) {
            this.drop = d;
        }


//    @Override
//    public void run() {
//        try {
//            for (Point point : points)
//                drop.put(point);
//            drop.put(new Point(0,0));
//        } catch (InterruptedException intEx) {
//            System.out.println("Interrupted! " +
//                    "Last one out, turn out the lights!");
//        }
//    }

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                Random random = new Random();
                int maxValue = 10;
                int minValue = -10;
                double valueX = random.nextInt(maxValue - minValue);
                double valueY = random.nextInt(maxValue - minValue);
                valueX += minValue;
                valueY += minValue;
                points.add(new Point(valueX, valueY));

                try {
                    while (!Thread.interrupted()) {
                        valueX = random.nextInt(maxValue - minValue);
                        valueY = random.nextInt(maxValue - minValue);
                        valueX += minValue;
                        valueY += minValue;
                        drop.put(new Point(valueX, valueY));
                        sleep(1000);        //Приостановка потока на 1 сек.
                    }
                    drop.put(new Point(0, 0));
                } catch (InterruptedException intEx) {
                    System.out.println("Interrupted Producer! ");
                    drop.add(new Point(0,0));
                    return;
                }

                try {
                    sleep(1000);        //Приостановка потока на 1 сек.
                } catch (InterruptedException e) {
                    return;    //Завершение потока после прерывания
                }
            }
        }


    }
    class Consumer extends Thread
            implements Runnable {
        private BlockingQueue<Point> drop;

        public Consumer(BlockingQueue<Point> d) {
            this.drop = d;
        }


        @Override
        public void run() {
            try {
                Point p;
                while (!((p = drop.take()).equals(new Point(0, 0))))
                    System.out.println(p.giveDistance());
                System.out.println("All points have been received or Producer had been interrupted");
            } catch (InterruptedException intEx) {
                System.out.println("Interrupted Consumer!");
            }
        }
    }
