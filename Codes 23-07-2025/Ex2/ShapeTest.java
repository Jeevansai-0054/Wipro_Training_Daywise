package com.wipro.oop;

public class ShapeTest {
    public static void main(String[] args) {
        Circle circle = new Circle(2.5, "red", false);
        Rectangle rectangle = new Rectangle(3.0, 4.0, "blue", true);
        Square square = new Square(2.0, "yellow", true);

        System.out.println(circle);
        System.out.println("Area: " + circle.getArea() + 
                           ", Perimeter: " + circle.getPerimeter());

        System.out.println(rectangle);
        System.out.println("Area: " + rectangle.getArea() + 
                           ", Perimeter: " + rectangle.getPerimeter());

        System.out.println(square);
        System.out.println("Area: " + square.getArea() + 
                           ", Perimeter: " + square.getPerimeter());
    }
}
