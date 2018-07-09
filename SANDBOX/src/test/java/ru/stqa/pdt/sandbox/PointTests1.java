package ru.stqa.pdt.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests1 {

  @Test
  public void testDistance() {

    //Error, must be 22.782063119919584
    Point p = new Point(-2.32, 5.0, 15, -9.8);
    Assert.assertEquals(p.distance(), 22.8);
  }
}
