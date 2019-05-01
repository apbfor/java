package com.apbfor;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Point center1 = new Point(4,5);
        Circle ass1 = new Circle(center1,5);
        Circle ass2 = new Circle(center1,6);
        System.out.println(ass1.equals(center1));

        List<Circle> circles = new ArrayList();
        List<Point>  points  = new ArrayList();

        int change;
        Scanner in = new Scanner(System.in);

        do {
            PrintMenu();
            change = in.nextInt();
            switch (change){
                case 1:{
                    System.out.print("X = ");
                    double x = in.nextDouble();
                    System.out.print("Y = ");
                    double y = in.nextDouble();

                    Point a = new Point(x,y);
                    points.add(a);
                    break;
                }
                case 2:{

                    System.out.print("X = ");
                    double x = in.nextDouble();
                    System.out.print("Y = ");
                    double y = in.nextDouble();
                    System.out.print("Radius = ");
                    double radius = in.nextDouble();

                    Point center = new Point(x,y);
                    Circle a = new Circle(center,radius);
                    circles.add(a);
                    break;
                }

                case 3:{
                    int changes;
                    PrintChange();
                    List<Point> points1 = new ArrayList<>();

                    for(int i = 0; i<points.size(); i++){
                        points1.add(points.get(i));
                    }

                    changes = in.nextInt();
                    switch (changes) {
                        case 1:
                            break;
                        case 2:{
                            points1.sort((o1, o2) -> o1.compareTox(o2));
                            break;
                        }
                        case 3:{
                            points1.sort((o1, o2) -> o1.compareTox(o2));
                            Collections.reverse(points1);
                            break;
                        }
                    }
                    for(Point point : points1)
                        point.draw();
                    break;
                }

                case 4:{
                    int changes;
                    PrintChange();
                    List<Circle> circles1 = new ArrayList<>();
                    for(int i=0; i<circles.size(); i++){
                        circles1.add(circles.get(i));
                    }

                    changes = in.nextInt();
                    switch (changes) {
                        case 1:
                            break;
                        case 2:{
                            circles1.sort((o1, o2) -> o1.compareTox(o2));
                            break;
                        }
                        case 3:{
                            circles1.sort((o1, o2) -> o1.compareTox(o2));
                            Collections.reverse(circles1);
                            break;
                        }
                    }
                    for(Circle circle :circles1)
                        circle.draw();
                    break;
                }

                case 5:{
                    double x, y;
                    System.out.print("X = ");
                    x = in.nextDouble();
                    System.out.print("Y = ");
                    y = in.nextDouble();
                    points.removeIf(p->p.getX()==x&&p.getY()==y);
                    break;
                    }
                case 6:{
                    double x,y,radius;
                    System.out.print("X = ");
                    x = in.nextDouble();
                    System.out.print("Y = ");
                    y = in.nextDouble();
                    System.out.print("Radius = ");
                    radius = in.nextDouble();
                    Point center = new Point(x,y);
                    Circle second = new Circle(center,radius);
                    circles.removeIf(p->p.equals(second));
                    break;
                }
                case 7:{
                    System.out.println("Точки, содержащиеся хотя бы в одной окружности : ");
                    PrintInside(points, circles);
                    break;
                }

                case 9:{
                    circles.sort((o1, o2) -> o1.compareTox(o2));
                    for(Circle circle :circles)
                        circle.draw();
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

        }

        public static void PrintInside(List<Point> points, List<Circle> circles){
           first: for (Point point : points){
                for (Circle circle : circles){
                    if (IsInside(point, circle)) {
                        point.draw();
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