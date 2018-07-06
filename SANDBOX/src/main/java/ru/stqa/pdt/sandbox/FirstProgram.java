package ru.stqa.pdt.sandbox;

public class FirstProgram {


  public static void main(String[] args) {
    hello("world");
    hello("user");
    hello("Anna");

    double l = 5;
    System.out.println("Площадь квадрата со стороной " + l + " = " + area(l));

    double x = 3;
    double y = 4;
    System.out.println("Площадь прямоугольника со сторонами " + x + " и " + y + " = " + area(x, y));
  }

  public static void hello(String somebody) {
    System.out.println("Hello," + somebody + "!");
  }

  public static double area(double len) {
    return len * len;
  }

  public static double area(double x, double y) {
    return x * y;
  }
}