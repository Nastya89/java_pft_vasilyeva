package ru.stqa.pft.vasilyeva.sandbox;

import home.task.first.vasilyeva.Point;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
    @Test
    public void testDistance() {

        Point p1 = new Point(5, 3);
        Point p2 = new Point(8, 1);
        double result = Math.sqrt((Math.pow(8 - 5, 2)) + Math.pow(1 - 3, 2));
        Assert.assertEquals(p1.distance(p2), Math.round(result));
    }
}
