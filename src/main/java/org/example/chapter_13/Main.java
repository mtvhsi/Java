package org.example.chapter_13;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String dbUrl = "jdbc:h2:~/test";
        String user = "sa";
        String password = "";
        try {
            TriangleDatabase db = new TriangleDatabase(dbUrl, user, password);

            // Добавление треугольников
            db.addTriangle(new Triangle(0, 0, 3, 0, 0, 4));
            db.addTriangle(new Triangle(0, 0, 1, 1, 2, 0));
            db.addTriangle(new Triangle(0, 0, 2, 0, 1, (float) Math.sqrt(3)));

            // Ближайший к площади 3
            float targetArea = 3;
            Triangle closeAreaTriangle = db.getTriangleCloseToArea(targetArea);
            System.out.println("Ближайший к площади " + targetArea + " треугольник: " + closeAreaTriangle);

            // Равнобедренные треугольники
            List<Triangle> isoscelesTriangles = db.getIsoscelesTriangles();
            System.out.println("Равнобедренные треугольники: " + isoscelesTriangles);

            // Равносторонние треугольники
            List<Triangle> equilateralTriangles = db.getEquilateralTriangles();
            System.out.println("Равносторонние треугольники: " + equilateralTriangles);

            // Треугольники, которые помещаются в окружность заданного радиуса
            double radius = 2.5;
            List<Triangle> trianglesInCircle = db.getTrianglesInCircumcircle(radius);
            System.out.println("Треугольники, помещающиеся в окружность радиуса " + radius + ": " + trianglesInCircle);

            // Сумма площадей, наиболее приближенная к заданному значению
            float targetSumArea = 5.0f; // Заданная площадь
            List<Triangle> closeSumAreaTriangles = db.getTrianglesCloseToSumArea(targetSumArea);
            System.out.println("Треугольники, сумма площадей которых наиболее приближена к " + targetSumArea + ": " + closeSumAreaTriangles);

            // Все прямоугольные треугольники
            List<Triangle> rightTriangles = db.getRightTriangles();
            System.out.println("Все прямоугольные треугольники: " + rightTriangles);

            // Все тупоугольные треугольники с площадью больше заданной
            float minArea = 2.0f; // Минимальная площадь
            List<Triangle> obtuseTriangles = db.getObtuseTrianglesWithAreaGreaterThan(minArea);
            System.out.println("Все тупоугольные треугольники с площадью больше " + minArea + ": " + obtuseTriangles);

            // Отключение базы данных
            db.close();
        } catch (SQLException e) {
            e.printStackTrace(); // Ошибки
        }
    }
}
