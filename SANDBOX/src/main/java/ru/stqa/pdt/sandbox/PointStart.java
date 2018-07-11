package ru.stqa.pdt.sandbox;

public class PointStart {

  public static void main(String[] args) {

    Point p1 = new Point(1, 40);
    Point p2 = new Point(-13.9, 0);
    Point p3 = new Point(0, -0.7);
    Point p4 = new Point(6, 8);
    System.out.println("Расстояние между точкой p1 с координатами (" + p1.x + ";" + p1.y + ") и точкой p2 с координатами (" + p2.x + ";" + p2.y + ") = " + distance(p1, p2));
    System.out.println("Расстояние между двумя точками p3(" + p3.x + ";" + p3.y + ") и p4(" + p4.x + ";" + p4.y + ") = " + p3.distance(p4));
  }

  public static double distance(Point a, Point b) {
    return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
  }

}
