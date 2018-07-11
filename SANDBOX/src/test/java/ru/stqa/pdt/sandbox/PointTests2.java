package ru.stqa.pdt.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import static ru.stqa.pdt.sandbox.PointStart.distance;

public class PointTests2 {

  @Test
  public void testDistance() {

    //Correct
    Point p1 = new Point(1, 40);
    Point p2 = new Point(-13.9, 0);
    Assert.assertEquals(distance(p1,p2), 42.685009078129525);
  }

}
