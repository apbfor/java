package com.apbfor;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {


        List<Circle> circles = new ArrayList();
        List<Point>  points  = new ArrayList();

        String path = "point.out";
        String path1 = "circle.out";


        int change;
        Scanner in = new Scanner(System.in);

        do {
            PrintMenu();
            change = in.nextInt();
            switch (change){
                case 1:{
                    System.out.print("X = ");
                    int x = in.nextInt();
                    System.out.print("Y = ");
                    int y = in.nextInt();

                    Point a = new Point(x,y);
                    points.add(a);
                    break;
                }
                case 2:{

                    System.out.print("X = ");
                    int x = in.nextInt();
                    System.out.print("Y = ");
                    int y = in.nextInt();
                    System.out.print("Radius = ");
                    int radius = in.nextInt();

                    Point center = new Point(x,y);
                    Circle a = new Circle(center,radius);
                    circles.add(a);
                    break;
                }

                case 3:{
                    int changes;
                    PrintChange();
                    changes = in.nextInt();
                    switch (changes) {
                        case 1:
                            break;
                        case 2:{
                            points.sort((o1, o2) -> o1.compareTox(o2));
                            break;
                        }
                        case 3:{
                            points.sort((o1, o2) -> o1.compareTox(o2));
                            Collections.reverse(points);
                            break;
                        }
                    }
                    for(Point point : points)
                        point.draw();
                    break;
                }

                case 4:{
                    int changes;
                    PrintChange();
                    changes = in.nextInt();
                    switch (changes) {
                        case 1:
                            break;
                        case 2:{
                            circles.sort((o1, o2) -> o1.compareTox(o2));
                            break;
                        }
                        case 3:{
                            circles.sort((o1, o2) -> o1.compareTox(o2));
                            Collections.reverse(circles);
                            break;
                        }
                    }
                    for(Circle circle :circles)
                        circle.draw();
                    break;
                }

                case 5:{
                    int x, y;
                    System.out.print("X = ");
                    x = in.nextInt();
                    System.out.print("Y = ");
                    y = in.nextInt();
                    points.removeIf(p->p.getX()==x&&p.getY()==y);
                    break;
                    }
                case 6:{
                    int x,y,radius;
                    System.out.print("X = ");
                    x = in.nextInt();
                    System.out.print("Y = ");
                    y = in.nextInt();
                    System.out.print("Radius = ");
                    radius = in.nextInt();
                    Point center = new Point(x,y);
                    Circle second = new Circle(center,radius);
                    circles.removeIf(p->p.equals(second));
                    break;
                }
                case 7: {
                    System.out.println("Точки, содержащиеся хотя бы в одной окружности : ");
                    PrintInside(points, circles);
                    break;
                }

            }

        }while (change!=0);

   }

        static void PrintChange() {
            System.out.println("1.Вывести в порядке, в котором осуществлялось добавление");
            System.out.println("2.Вывести в порядке возрастания значения координаты Х");
            System.out.println("3.Вывести в порядке убывания значения координаты Х");
        }
        static void PrintMenu() {

            System.out.println("Выберите операцию : ");
            System.out.println("1.Добавить точку в список");
            System.out.println("2.Добавить окружность в список");
            System.out.println("3.Вывод на экран списка точек");
            System.out.println("4.Вывод на экран списка окружностей");
            System.out.println("5.Удаление из списка точек одной");
            System.out.println("6.Удаление из списка окружностей одной");
            System.out.println("7.Вывод на экран точек, находящихся внутри хотя бы одной окружности" +
                    " из списка окружностей");
            System.out.println("0.Выход");

        }


        public static void PrintInside(List<Point> points, List<Circle> circles){
           first: for (Point point : points){
                for (Circle circle : circles){
                    if (IsInside(point, circle)) {
                        System.out.println("Точка с координатами X = "+point.getX()+"; Y = "+point.getY()+
                                " находится в окружности с координатами X = "+circle.getX()+"; Y = "+
                                circle.getY()+"; радиуса : "+circle.getRadius());
                        continue first;
                    }
                }
            }
        }

        public static boolean IsInside(Point point,Circle circle){
            double x = point.getX();
            double y = point.getY();
            double a = circle.getX();
            double b = circle.getY();
            double r = circle.getRadius();
            double dx = x-a;
            double dy = y-b;
            boolean result = r*r<=dx*dx+dy*dy;

            return !result;

        }
}