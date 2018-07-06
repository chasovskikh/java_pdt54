package ru.stqa.pdt.sandbox;

public class FirstProgram {


  public static void main(String[] args) {
    hello("world");
    hello("user");
    hello("Anna");

    Square s = new Square(6);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(2, 8);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

    //function
    distance(2.12, 3.56, -4.09, 5);


    //class
    Point p = new Point(1.0, -2.8, 3.13, 6.54);
    System.out.println("Расстояние между точкой А с координатами (" + p.x1 + ";" + p.y1 + ") и точкой В с координатами (" + p.x2 + ";" + p.y2 + ") = " + p.distance());
  }

  public static void hello(String somebody) {
    System.out.println("Hello," + somebody + "!");
  }

  public static void distance(double x1, double y1, double x2, double y2) {

    double dist = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    System.out.println("Даны точка X с координатами (" + x1 + ";" + y1 + ") и точка Y с координатами (" + x2 + ";" + y2 + ") \n" + "Расстояние между этими точками " + dist);

  }


}
