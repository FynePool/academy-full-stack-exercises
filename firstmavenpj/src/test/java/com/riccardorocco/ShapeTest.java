package com.riccardorocco;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class ShapeTest {

    @Test
    public void ShapesAreSortableOnArea() {
        // Arrange
        double area, side, radius, base, height, width;
        ArrayList<Shape> expected = new ArrayList<Shape>();

        area = 1.1234;
        expected.add(new CustomShape(area));

        side = 1.1234;
        expected.add(new Square(side));

        radius = 1.1234;
        expected.add(new Circle(radius));

        height = 2.;
        base = 5.;
        expected.add(new Triangle(base, height));

        height = 3.;
        base = 4.;
        expected.add(new Triangle(base, height));

        width = 4.;
        expected.add(new Rectangle(width, height));

        area = 16.1;
        expected.add(new CustomShape(area));

        Random random = new Random();
        ArrayList<Shape> actual = createRandomOrderedList(random, expected);

        // Act
        Collections.sort(actual);

        // Assert
        Iterator<Shape> a = actual.iterator();
        for (Shape e : expected) {
            assertEquals(e, a.next());
        }
    }

    private ArrayList<Shape> createRandomOrderedList(Random random, ArrayList<Shape> expected) {
        ArrayList<Shape> actual = new ArrayList<Shape>();
        for (Shape shape : expected) {
            int j = random.nextInt(actual.size() + 1);
            actual.add(j, shape);
        }
        return actual;
    }
}
