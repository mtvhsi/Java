package org.example.chapter_13;
public class Triangle {
    private float x1, y1, x2, y2, x3, y3;

    public Triangle(float x1, float y1, float x2, float y2, float x3, float y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    public float getX1() { return x1; }
    public float getY1() { return y1; }
    public float getX2() { return x2; }
    public float getY2() { return y2; }
    public float getX3() { return x3; }
    public float getY3() { return y3; }

    public boolean isIsosceles() {
        float side1 = distance(x1, y1, x2, y2);
        float side2 = distance(x2, y2, x3, y3);
        float side3 = distance(x1, y1, x3, y3);
        return side1 == side2 || side2 == side3 || side1 == side3;
    }

    public boolean isEquilateral() {
        float side1 = distance(x1, y1, x2, y2);
        float side2 = distance(x2, y2, x3, y3);
        float side3 = distance(x1, y1, x3, y3);
        return side1 == side2 && side2 == side3;
    }

    private float distance(float x1, float y1, float x2, float y2) {
        return (float) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    @Override
    public String toString() {
        return String.format("Triangle: [(x1, y1): (%.2f, %.2f), (x2, y2): (%.2f, %.2f), (x3, y3): (%.2f, %.2f)]", x1, y1, x2, y2, x3, y3);
    }
}
