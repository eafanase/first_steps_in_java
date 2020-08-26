package ru.stqa.katja.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    Point p1 = new Point(2,3);
    Point p2 = new Point(5,8);
    System.out.println("Расстояние между двумя точками " + "= " + p1.distance(p2));
  }
}
