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

  }

  public static void hello(String somebody) {
    System.out.println("Hello," + somebody + "!");
  }

 }
