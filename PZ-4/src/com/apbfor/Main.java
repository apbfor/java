package com.apbfor;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        List<Circle> circles = new ArrayList();
        List<Point>  points  = new ArrayList();

        try (BufferedReader br = new BufferedReader(new FileReader("circles.txt"))){ //читаем дамп окружностей
            String s;
            while ((s=br.readLine())!=null) { //берем строку, пока она есть в файле
                String[] one = s.split(" "); //разбиваем строку на массив строк
                double x = Double.parseDouble(one[0]);//зная структуру строки и, соответсвенно, массива, парсим x,y,r
                double y = Double.parseDouble(one[1]);
                double radius = Double.parseDouble(one[2]);

                circles.add(new Circle(new Point(x,y),radius));
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

        try (BufferedReader br = new BufferedReader(new FileReader("points.txt"))){ //читаем дамп точек
            String s;
            while ((s=br.readLine())!=null) { //берем строку, пока она есть в файле
                String[] one = s.split(" ");//разбиваем строку на массив строк
                double x = Double.parseDouble(one[0]);//зная структуру строки и, соответсвенно, массива, парсим x и y
                double y = Double.parseDouble(one[1]);

                points.add(new Point(x, y));
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

        int change;
        Scanner in = new Scanner(System.in);
        FileWriter fw;
        do {
            PrintMenu();
            change = in.nextInt();
            switch (change){
                case 1:{ //добавление точки в список
                    System.out.print("X = ");
                    double x = in.nextDouble();
                    System.out.print("Y = ");
                    double y = in.nextDouble();
                    points.add(new Point(x,y));
                    break;
                }
                case 2:{ //добавление окружности в список
                    System.out.print("X = ");
                    double x = in.nextDouble();
                    System.out.print("Y = ");
                    double y = in.nextDouble();
                    System.out.print("Radius = ");
                    double radius = in.nextDouble();
                    circles.add(new Circle(new Point(x,y),radius));
                    break;
                }

                case 3:{ //печать списка точек
                    int changes;
                    PrintChange();
                    List<Point> points1 = new ArrayList<>();

                    for(int i = 0; i<points.size(); i++){
                        points1.add(points.get(i));
                    }

                    //порядок печати списка точек
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

                case 4:{ //печать списка окружностей
                    int changes;
                    PrintChange();
                    List<Circle> circles1 = new ArrayList<>();
                    for(int i=0; i<circles.size(); i++){
                        circles1.add(circles.get(i));
                    }
                    //порядок печати списка окружностей
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

                case 5:{ //удаление из списка определенной точки
                    double x, y;
                    System.out.print("X = ");
                    x = in.nextDouble();
                    System.out.print("Y = ");
                    y = in.nextDouble();
                    points.removeIf(p->p.getX()==x&&p.getY()==y);
                    break;
                    }
                case 6:{ //удаление из списка окружности
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

                case 8: { //сохранение точек в файл
                    int changes;
                    PrintChange();
                    List<Point> points1 = new ArrayList<>();

                    for (int i = 0; i < points.size(); i++) {
                        points1.add(points.get(i));
                    }

                    changes = in.nextInt();
                    switch (changes) { //выбираем порядок, в котором будем сохранять
                        case 1:
                            break;
                        case 2: {
                            points1.sort((o1, o2) -> o1.compareTox(o2));
                            break;
                        }
                        case 3: {
                            points1.sort((o1, o2) -> o1.compareTox(o2));
                            Collections.reverse(points1);
                            break;
                        }
                    }
                    String path;
                    System.out.println("Введите путь/название файла");
                    path = in.next();
                    File file = new File(path);
                    if(file.exists()){ //если такой файл уже есть
                        int fileExists;
                        PrintFileExists();
                        fileExists = in.nextInt();
                        switch (fileExists){
                            case 1:{ //дописываем в конец
                                fw = new FileWriter(file,true);
                                for(int i=0; i<points1.size(); i++){
                                    fw.write(points1.get(i).toString1());
                                    fw.write("\n");
                                }
                                fw.close();
                                break;
                            }
                            case 2:{ //перезаписываем
                                fw = new FileWriter(file);
                                for(int i=0; i<points1.size(); i++){
                                    fw.write(points1.get(i).toString1());
                                    fw.write("\n");
                                }
                                fw.close();
                                break;
                            }
                            case 3: //отказались от записи
                                break;
                        }
                    }
                    else { //если файла нет, со спокойной душой записываем как новый
                        fw = new FileWriter(file);
                        for(int i=0; i<points1.size(); i++){
                            fw.write(points1.get(i).toString1());
                            fw.write("\n");
                        }
                        fw.close();
                    }
                    break;
                }
                case 9:{ //сохранение окружностей в файл
                    int changes;
                    PrintChange();
                    List<Circle> circles1 = new ArrayList<>();

                    for(int i = 0; i<circles.size(); i++){
                        circles1.add(circles.get(i));
                    }

                    changes = in.nextInt();
                    switch (changes) { //выбираем порядок сохранения
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
                    String path;
                    System.out.println("Введите путь/название файла");
                    path = in.next();
                    File file = new File(path);
                    if(file.exists()){ //если такой файл есть
                        int fileExists;
                        PrintFileExists();
                        fileExists = in.nextInt();
                        switch (fileExists){
                            case 1:{ //дописываем
                                fw = new FileWriter(file,true);
                                for(int i=0; i<circles1.size(); i++){
                                    fw.write(circles1.get(i).toString1());
                                    fw.write("\n");
                                }
                                fw.close();
                                break;
                            }
                            case 2:{ //перезаписываем
                                fw = new FileWriter(file);
                                for(int i=0; i<circles1.size(); i++){
                                    fw.write(circles1.get(i).toString1());
                                    fw.write("\n");
                                }
                                fw.close();
                                break;
                            }
                            case 3: //выходим без записи
                                break;
                        }
                    }
                    else { //если файла нет, создаем и пишем
                        fw = new FileWriter(file);
                        for(int i=0; i<circles1.size(); i++){
                            fw.write(circles1.get(i).toString1());
                            fw.write("\n");
                        }
                        fw.close();
                    }


                    break;
                }


            }



        }while (change!=0);

        // Здесь реализовано какое-то подобие сохранения всех данных в "дамп файлы"

        fw = new FileWriter("circles.txt");

        for(int i=0; i<circles.size(); i++){
            fw.write(circles.get(i).toString());
            fw.write("\n");
        }
        fw.close();

        fw = new FileWriter("points.txt");
        for(int i=0; i<points.size(); i++){
            fw.write(points.get(i).toString());
            fw.write("\n");
        }
        fw.close();


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
            System.out.println("8. Сохранение в файл точек");
            System.out.println("9. Сохранение в файл окружностей");

        }

        static void PrintFileExists(){
            System.out.println("Файл уже существует. Выберите :");
            System.out.println("1. Дописать данные в конец файла");
            System.out.println("2. Заменить содержимое файла");
            System.out.println("3. Не записывать ничего");
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