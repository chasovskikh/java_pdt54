package ru.stqa.pdt.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests2 {

  @Test
  public void testDistance() {

    //Correct
    Point p = new Point(1,2,1,4);
    Assert.assertEquals(p.distance(), 2.0);
  }
}
