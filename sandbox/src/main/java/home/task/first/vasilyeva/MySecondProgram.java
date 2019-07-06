package home.task.first.vasilyeva;

public class MySecondProgram {
    public static void main(String[] args) {
        Point point = new Point(3.5, 4.8);

        Point point2 = new Point(2.7, 8.1);
        double result = distance(point, point2);
        System.out.println("Расстояние между двумя точками: " + result);
    }

    public static double distance(Point p1, Point p2) {
        double distance = Math.sqrt((Math.pow(p2.pointX - p1.pointX, 2)) + Math.pow(p2.pointY - p1.pointY, 2));
        return Math.round(distance);
    }
}
