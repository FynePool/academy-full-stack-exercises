package com.riccardorocco;

public abstract class Shape implements Comparable<Shape>{
    public abstract double getArea();
    @Override
    public int compareTo(Shape arg0) {
      return (this.getArea() < ((Shape) arg0).getArea()) ? -1 : ((this.getArea() == ((Shape) arg0).getArea()) ? 0 : 1);
    }
}

class Square extends Shape {
    private double side;

    public Square(double side){
        this.side=side;
    }

    @Override
    public double getArea(){
       return side*side;
    }
}

class CustomShape extends Shape{
    private double area;

    public CustomShape(double area){
        this.area = area;
    }

    @Override
    public double getArea(){
       return this.area;
    }
}

class Circle extends Shape{
    private double radius; 

    public Circle(double radius){
        this.radius=radius;
    }


    @Override
    public double getArea(){
       return (this.radius * this.radius) * Math.PI;
    }
}

class Rectangle extends Shape{
    private double width, height;

    public Rectangle(double width , double height){
        this.width=width;
        this.height=height;
    }

    @Override
    public double getArea(){
       return this.width*this.height;
    }
}

class Triangle extends Shape{
    private double base, height;
    public Triangle(double base, double height){
        this.base=base;
        this.height=height;
    }

    @Override
    public double getArea(){
       return (this.base * this.height) / 2;
    }
}