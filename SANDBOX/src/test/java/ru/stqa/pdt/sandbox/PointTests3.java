package ru.stqa.pdt.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;


public class PointTests3 {

  @Test
  public void testDistance() {

    //Error
    Point p1 = new Point(0,0);
    Point p2 = new Point(100, 0);
    Assert.assertEquals(PointStart.distance(p1,p2), 100);
  }

}
