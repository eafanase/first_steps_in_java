package ru.stqa.katja.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTest {
  @Test
  public void testDistance1() {
    Point p1 = new Point(2,3);
    Point p2 = new Point(5,8);
    assert p1.distance(p2) == 5.830951894845301;

  }
  @Test
  public void testDistance2() {
    Point p1 = new Point(2, 3);
    Point p2 = new Point(5, 8);
    Assert.assertEquals(p1.distance(p2), 5.830951894845302);
  }
  @Test
  public void testDistance3() {
    Point p1 = new Point(2, 3);
    Point p2 = new Point(-5, -8);
    Assert.assertEquals(p1.distance(p2), 5.830951894845301);
  }
}
