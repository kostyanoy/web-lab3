package model;

// class with specific conditions for image
public class CheckUtils {
    public static boolean isInsideRectangle(double x, double y, double height, double width) {
        return Math.abs(x) <= width && Math.abs(y) <= height;
    }

    public static boolean isInsideCircle(double x, double y, double radius) {
        return x * x + y * y <= radius * radius;
    }

    public static boolean isInsideRhombus(double x, double y, double height, double width) {
        return Math.abs(x) / width + Math.abs(y) / height <= 1;
    }

    // depends on image
    public static boolean makeFullCheck(double x, double y, double r) {
        return (x >= 0 && y >= 0 && isInsideCircle(x, y, r))
                || (x >= 0 && y <= 0 && isInsideRhombus(x, y, r / 2, r / 2))
                || (x <= 0 && y <= 0 && isInsideRectangle(x, y, r / 2, r));
    }
}
