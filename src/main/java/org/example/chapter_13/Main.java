package org.example.chapter_13;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String dbUrl = "jdbc:h2:tcp://localhost/~/test";
        String user = "sa";
        String password = "";
        try {
            TriangleDatabase db = new TriangleDatabase(dbUrl, user, password);

            db.addTriangle(new Triangle(0, 0, 3, 0, 0, 4));
            db.addTriangle(new Triangle(0, 0, 1, 1, 2, 0));
            db.addTriangle(new Triangle(0, 0, 2, 0, 1, (float) Math.sqrt(3)));

            float targetArea = 3;
            Triangle closeAreaTriangle = db.getTriangleCloseToArea(targetArea);
            System.out.println("Ближайший к площади " + targetArea + " треугольник: " + closeAreaTriangle);

            List<Triangle> isoscelesTriangles = db.getIsoscelesTriangles();
            System.out.println("Равнобедренные треугольники: " + isoscelesTriangles);

            List<Triangle> equilateralTriangles = db.getEquilateralTriangles();
            System.out.println("Равносторонние треугольники: " + equilateralTriangles);

            double radius = 2.5;
            List<Triangle> trianglesInCircle = db.getTrianglesInCircumcircle(radius);
            System.out.println("Треугольники, помещающиеся в окружность радиуса " + radius + ": " + trianglesInCircle);

            float targetSumArea = 5.0f;
            List<Triangle> closeSumAreaTriangles = db.getTrianglesCloseToSumArea(targetSumArea);
            System.out.println("Треугольники, сумма площадей которых наиболее приближена к " + targetSumArea + ": " + closeSumAreaTriangles);

            List<Triangle> rightTriangles = db.getRightTriangles();
            System.out.println("Все прямоугольные треугольники: " + rightTriangles);

            float minArea = 2.0f;
            List<Triangle> obtuseTriangles = db.getObtuseTrianglesWithAreaGreaterThan(minArea);
            System.out.println("Все тупоугольные треугольники с площадью больше " + minArea + ": " + obtuseTriangles);

            db.close();
        } catch (SQLException e) {
            e.printStackTrace(); ////ошибки
        }
    }
}
