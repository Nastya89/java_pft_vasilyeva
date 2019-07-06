package home.task.first.vasilyeva;

public class MySecondProgram {
    public static void main(String[] args) {
        Point point = new Point(3.5, 4.8);
        Point point2 = new Point(2.7, 8.1);
        double result = point.distance(point2);
        System.out.println("Расстояние между двумя точками: " + result);
    }
}
