package ru.stqa.pdt.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests1 {

  @Test
  public void testDistance() {

    //Error, must be 42.685009078129525
    Point p3 = new Point(0, -0.7);
    Point p4 = new Point(6, 8);
    Assert.assertEquals(p3.distance(p4), 10);
  }
}
