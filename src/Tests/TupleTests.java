package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import Models.Tuples.Point;
import Models.Tuples.Vector;

public class TupleTests {

    @Test
    @DisplayName("Create Point")
    public void createPoint() {

        Point a = new Point(4, -4, 3);

        assertEquals(1, a.getW(), "Point not initiated correctly");
    }

    @Test
    @DisplayName("Create Vector")
    public void createVector() {

        Vector a = new Vector(4, -4, 3);

        assertEquals(0, a.getW(), "Vector not initiated correctly");
    }

    @Test
    @DisplayName("Compare Tuples")
    public void Equals() {

        Vector a = new Vector(4, -4, 3);
        Vector b = new Vector(4, -4, 3);

        Point c = new Point(4, -4, 3);
        Point d = new Point(4, -4, 3);

        assertEquals(true, a.equals(b), "Vector Equals method is not valid");
        assertEquals(true, c.equals(d), "Point Equals method is not valid");
        assertEquals(false, a.equals(c), "Vector canot equal Point");
    }

    @Test
    @DisplayName("Add Tuples")
    public void Add() {

        Vector a = new Vector(1, 0, 1);
        Vector b = new Vector(1, 0, 1);

        Point c = new Point(1, 0, 1);

        Vector res1 = a.add(b);
        Point res2 = c.add(a);

        assertEquals(0, res1.getW(), "Tuple Add method is not valid for vector vector opperations");
        assertEquals(1, res2.getW(), "Tuple Add method is not valid for vector point opperations");
    }

    @Test
    @DisplayName("Subtract Tuples")
    public void Subtract() {

        Vector a = new Vector(1, 0, 1);
        Vector b = new Vector(1, 0, 1);

        Point c = new Point(1, 0, 1);
        Point d = new Point(1, 0, 1);

        Vector res1 = a.subtract(b);
        Vector res2 = c.subtract(a);
        Vector res3 = c.subtract(d);

        assertEquals(0, res1.getX(), "Tuple Subtract method is not valid for vector vector opperations");
        assertEquals(0, res2.getW(), "Tuple Subtract method is not valid for vector point opperations");
        assertEquals(0, res3.getZ(), "Tuple Subtract method is not valid for point point opperations");
    }
}
