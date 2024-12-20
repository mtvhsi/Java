package org.example.chapter_13;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TriangleDatabase {
    private Connection connection;

    public TriangleDatabase(String dbUrl, String user, String password) throws SQLException {
        try {
            Class.forName("org.h2.Driver");
            this.connection = DriverManager.getConnection(dbUrl, user, password);
            createTable();
        } catch (ClassNotFoundException e) {
            System.out.println("H2 Driver class not found.");
            e.printStackTrace();
            throw new SQLException("H2 Driver not found", e);
        }
    }


    private void createTable() throws SQLException {
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS triangles (
                id INT AUTO_INCREMENT PRIMARY KEY,
                x1 FLOAT NOT NULL,
                y1 FLOAT NOT NULL,
                x2 FLOAT NOT NULL,
                y2 FLOAT NOT NULL,
                x3 FLOAT NOT NULL,
                y3 FLOAT NOT NULL
            )
        """;
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSQL);
        }
    }

    public void addTriangle(Triangle triangle) throws SQLException {
        String insertSQL = "INSERT INTO triangles (x1, y1, x2, y2, x3, y3) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setFloat(1, triangle.getX1());
            pstmt.setFloat(2, triangle.getY1());
            pstmt.setFloat(3, triangle.getX2());
            pstmt.setFloat(4, triangle.getY2());
            pstmt.setFloat(5, triangle.getX3());
            pstmt.setFloat(6, triangle.getY3());
            pstmt.executeUpdate();
        }
    }

    public Triangle getTriangleCloseToArea(float targetArea) throws SQLException {
        String querySQL = """
            SELECT x1, y1, x2, y2, x3, y3
            FROM triangles
            ORDER BY ABS(0.5 * Math.abs(x1*(y2-y3) + x2*(y3-y1) + x3*(y1-y2)) - ?) LIMIT 1
        """;
        try (PreparedStatement pstmt = connection.prepareStatement(querySQL)) {
            pstmt.setFloat(1, targetArea);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Triangle(rs.getFloat("x1"), rs.getFloat("y1"), rs.getFloat("x2"), rs.getFloat("y2"), rs.getFloat("x3"), rs.getFloat("y3"));
            }
        }
        return null;
    }


    public List<Triangle> getIsoscelesTriangles() throws SQLException {
        return getTrianglesByType("isosceles");
    }

    public List<Triangle> getEquilateralTriangles() throws SQLException {
        return getTrianglesByType("equilateral");
    }

    public List<Triangle> getTrianglesInCircumcircle(double radius) throws SQLException {
        //
    }

    public List<Triangle> getTrianglesCloseToSumArea(float targetArea) throws SQLException {
        //
    }

    public List<Triangle> getRightTriangles() throws SQLException {
        //
    }

    public List<Triangle> getObtuseTrianglesWithAreaGreaterThan(float area) throws SQLException {
        //
    }

    private List<Triangle> getTrianglesByType(String type) throws SQLException {
        String querySQL = """
            SELECT x1, y1, x2, y2, x3, y3 FROM triangles
        """;
        List<Triangle> triangles = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(querySQL);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Triangle triangle = new Triangle(rs.getFloat("x1"), rs.getFloat("y1"), rs.getFloat("x2"), rs.getFloat("y2"), rs.getFloat("x3"), rs.getFloat("y3"));
                if (isTriangleOfType(triangle, type)) {
                    triangles.add(triangle);
                }
            }
        }
        return triangles;
    }

    private boolean isTriangleOfType(Triangle triangle, String type) {
        switch (type) {
            case "равнобедренные":
                return triangle.isIsosceles();
            case "равносторонние":
                return triangle.isEquilateral();
            default:
                return false;
        }
    }

    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
