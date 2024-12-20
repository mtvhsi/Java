package org.example.chapter_13;
import java.util.Objects;

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
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Triangle)) return false;
        Triangle other = (Triangle) obj;
        return Float.compare(other.x1, x1) == 0 &&
                Float.compare(other.y1, y1) == 0 &&
                Float.compare(other.x2, x2) == 0 &&
                Float.compare(other.y2, y2) == 0 &&
                Float.compare(other.x3, x3) == 0 &&
                Float.compare(other.y3, y3) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1, y1, x2, y2, x3, y3);
    }

    public float getX1() { return x1; }
    public float getY1() { return y1; }
    public float getX2() { return x2; }
    public float getY2() { return y2; }
    public float getX3() { return x3; }
    public float getY3() { return y3; }

    public boolean isEquilateral() {
        float side1 = distance(x1, y1, x2, y2);
        float side2 = distance(x2, y2, x3, y3);
        float side3 = distance(x3, y3, x1, y1);
        return Float.compare(side1, side2) == 0 && Float.compare(side2, side3) == 0;
    }

    public boolean isIsosceles() {
        float side1 = distance(x1, y1, x2, y2);
        float side2 = distance(x2, y2, x3, y3);
        float side3 = distance(x3, y3, x1, y1);
        return Float.compare(side1, side2) == 0 || Float.compare(side2, side3) == 0 || Float.compare(side3, side1) == 0;
    }
    public boolean isRight() {
        float side1 = distance(x1, y1, x2, y2);
        float side2 = distance(x2, y2, x3, y3);
        float side3 = distance(x3, y3, x1, y1);
        return Float.compare(side1 * side1 + side2 * side2, side3 * side3) == 0 ||
                Float.compare(side2 * side2 + side3 * side3, side1 * side1) == 0 ||
                Float.compare(side3 * side3 + side1 * side1, side2 * side2) == 0;
    }

    public boolean isObtuse() {
        float side1 = distance(x1, y1, x2, y2);
        float side2 = distance(x2, y2, x3, y3);
        float side3 = distance(x3, y3, x1, y1);
        return Float.compare(side1 * side1 + side2 * side2, side3 * side3) < 0 ||
                Float.compare(side2 * side2 + side3 * side3, side1 * side1) < 0 ||
                Float.compare(side3 * side3 + side1 * side1, side2 * side2) < 0;
    }

    public boolean fitsInCircumcircle(double radius) {
        return getCircumradius() <= radius;
    }
    private float distance(float x1, float y1, float x2, float y2) {
        return (float) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    private double getCircumradius() {
        float a = distance(x1, y1, x2, y2);
        float b = distance(x2, y2, x3, y3);
        float c = distance(x3, y3, x1, y1);
        float area = getArea();
        return (a * b * c) / (4 * area);
    }

    public float getArea() {
        return 0.5f * Math.abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2));
    }
    @Override
    public String toString() {
        return String.format("Triangle[(%.2f, %.2f), (%.2f, %.2f), (%.2f, %.2f)]",
                x1, y1, x2, y2, x3, y3);
    }
}
