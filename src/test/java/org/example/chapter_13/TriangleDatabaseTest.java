package org.example.chapter_13;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TriangleDatabaseTest {
    private TriangleDatabase triangleDatabase;

    @BeforeEach
    void setUp() throws SQLException {
        String dbUrl = "jdbc:h2:mem:test" + System.nanoTime() + ";DB_CLOSE_DELAY=-1"; //бд для тестов
        triangleDatabase = new TriangleDatabase(dbUrl, "sa", "");
    }

    @AfterEach
    void tearDown() throws SQLException {
        triangleDatabase.close();
    }

    @Test
    void testAddTriangle() throws SQLException {
        Triangle triangle = new Triangle(0, 0, 3, 0, 0, 4);
        triangleDatabase.addTriangle(triangle);
        List<Triangle> triangles = triangleDatabase.getTrianglesCloseToSumArea(6);
        assertEquals(1, triangles.size());
        assertEquals(triangle, triangles.get(0));
    }
    @Test
    void testGetTriangleCloseToArea() throws SQLException {
        Triangle triangle1 = new Triangle(0, 0, 3, 0, 0, 4);
        Triangle triangle2 = new Triangle(1, 1, 2, 2, 3, 3);
        triangleDatabase.addTriangle(triangle1);
        triangleDatabase.addTriangle(triangle2);

        Triangle closest = triangleDatabase.getTriangleCloseToArea(6);
        assertNotNull(closest);
        assertEquals(triangle1, closest);
    }

    @Test
    void testGetObtuseTrianglesWithAreaGreaterThan() throws SQLException {
        Triangle triangle1 = new Triangle(0, 0, 4, 0, 0, 3);
        Triangle triangle2 = new Triangle(0, 0, 2, 2, 2, 0);
        triangleDatabase.addTriangle(triangle1);
        triangleDatabase.addTriangle(triangle2);

        List<Triangle> obtuseTriangles = triangleDatabase.getObtuseTrianglesWithAreaGreaterThan(5);
        assertEquals(1, obtuseTriangles.size());
        assertEquals(triangle1, obtuseTriangles.get(0));
    }

    @Test
    void testGetTrianglesInCircumcircle() throws SQLException {
        Triangle expectedTriangle = new Triangle(0, 0, 2, 2, 2, 0);
        triangleDatabase.addTriangle(expectedTriangle);
        Triangle otherTriangle = new Triangle(0, 0, 3, 0, 0, 4);
        triangleDatabase.addTriangle(otherTriangle);

        List<Triangle> trianglesInCircumcircle = triangleDatabase.getTrianglesInCircumcircle(2.5);

        assertEquals(2, trianglesInCircumcircle.size());
        assertEquals(expectedTriangle, trianglesInCircumcircle.get(0));
    }
}
