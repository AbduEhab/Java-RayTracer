package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import Models.Point;
import Models.Vector;

public class TupleTests {

    @Test
    @DisplayName("Create Point")
    public void createPoint() {

        Point a = new Point(4, -4, 3);

        assertEquals(a.getW(), 0, "Point not initiated correctly");
    }

    @Test
    @DisplayName("Create Vector")
    public void createVector() {

        Vector a = new Vector(4, -4, 3);

        assertEquals(a.getW(), 1, "Vector not initiated correctly");
    }

    @Test
    @DisplayName("Compare Tuples")
    public void Equals() {

        Vector a = new Vector(4, -4, 3);
        Vector b = new Vector(4, -4, 3);

        Point c = new Point(4, -4, 3);
        Point d = new Point(4, -4, 3);

        assertEquals(a.equals(b), true, "Vector Equals method is not valid");
        assertEquals(c.equals(d), true, "Point Equals method is not valid");
        assertEquals(a.equals(c), false, "Vector canot equal Point");
    }

    @Test
    @DisplayName("Add Tuples")
    public void Add() {

        Vector a = new Vector(1, 0, 1);
        Vector b = new Vector(1, 0, 1);

        Point c = new Point(1, 0, 1);
        Point d = new Point(1, 0, 1);

        Vector res1 = a.add(b);
        Vector res2 = a.add(c);
        Vector res3 = c.add(d);

        assertEquals(res1.getW(), 0, "Tuple Add method is not valid for vector vector opperations");
        assertEquals(res2.getW(), 1, "Tuple Add method is not valid for vector point opperations");
        assertEquals(res3.getW(), 2, "Tuple Add method is not valid for point point opperations");
    }

    @Test
    @DisplayName("Subtract Tuples")
    public void Subtract() {

        Vector a = new Vector(1, 0, 1);
        Vector b = new Vector(1, 0, 1);

        Point c = new Point(1, 0, 1);
        Point d = new Point(1, 0, 1);

        Vector res1 = a.subtract(b);
        Vector res2 = a.subtract(c);
        Vector res3 = c.subtract(d);

        assertEquals(res1.getW(), 0, "Tuple Subtract method is not valid for vector vector opperations");
        assertEquals(res2.getW(), -1, "Tuple Subtract method is not valid for vector point opperations");
        assertEquals(res3.getW(), 0, "Tuple Subtract method is not valid for point point opperations");
    }
}
