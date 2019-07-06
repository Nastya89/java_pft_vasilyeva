package home.task.first.vasilyeva;

public class Point {
    public double pointX;
    public double pointY;

    public Point(double pointX, double pointY) {
        this.pointX = pointX;
        this.pointY = pointY;
    }

    public double distance(Point p) {
        double distance = Math.sqrt((Math.pow(pointX - p.pointX, 2)) + Math.pow(pointY - p.pointY, 2));
        return Math.round(distance);
    }
}