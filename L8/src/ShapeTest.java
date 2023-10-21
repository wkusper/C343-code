class Shape {
    int area(int length) {
        return 0;
    }
}

class Square extends Shape {
    int area(int length) {
        return length * length;
    }
}

class Cube extends Square {
    int area(int length) {
        int faceArea = super.area(length);
        int surfaceArea = 6 * faceArea;
        return surfaceArea;
    }
}


public class ShapeTest {
    public static void main(String[] args) {
        Square square = new Square();
        Cube cube = new Cube();

        System.out.println("Area of square: " + square.area(5));
        System.out.println("Surface area of cube: " + cube.area(5));
    }
}
