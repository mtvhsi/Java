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
            ////добавление треугольников
            db.addTriangle(new Triangle(0, 0, 3, 0, 0, 4));
            db.addTriangle(new Triangle(0, 0, 1, 1, 2, 0));
            db.addTriangle(new Triangle(0, 0, 2, 0, 1, (float)Math.sqrt(3)));
            ////ближайшего к площади 3
            float targetArea = 3;
            Triangle closeAreaTriangle = db.getTriangleCloseToArea(targetArea);
            System.out.println("Ближайший к площади 3 треугольник" + targetArea + ": " + closeAreaTriangle);
            ////равнобедренные треугольники
            List<Triangle> isoscelesTriangles = db.getIsoscelesTriangles();
            System.out.println("Равнобедренные треугольники: " + isoscelesTriangles);
            /////равносторонние треугольники
            List<Triangle> equilateralTriangles = db.getEquilateralTriangles();
            System.out.println("Равносторонние треугольники: " + equilateralTriangles);

            ////откл бд
            db.close();
        } catch (SQLException e) {
            e.printStackTrace(); //ошибки
        }
    }
}
