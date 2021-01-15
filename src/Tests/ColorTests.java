package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import Models.Color;

public class ColorTests {

    @Test
    @DisplayName("Add Color")
    public void Add() {

        Color a = new Color(1, 0, 1);
        Color b = new Color(1, 0, 1);

        Color res1 = a.add(b);

        assertEquals(0, res1.getW(), "Tuple Add method is not valid for vector vector opperations");
    }

    @Test
    @DisplayName("Subtract Color")
    public void Subtract() {

        Color a = new Color(1, 0, 1);
        Color b = new Color(1, 0, 1);

        Color res1 = a.subtract(b);

        assertEquals(0, res1.getX(), "Tuple Subtract method is not valid for vector vector opperations");
    }

    @Test
    @DisplayName("Multiply scaler")
    public void multiply() {

        Color a = new Color(1, 0, 1);

        Color res = a.multiply(2);

        assertEquals(2, res.getX(), "Color multiplication method is not valid");
    }

    @Test
    @DisplayName("Multiply Color")
    public void multiplyColor() {

        Color a = new Color(1, 0, 1);

        Color res = a.multiply(new Color(3, 0, 3));

        assertEquals(3, res.getX(), "Color multiplication method is not valid");
    }
}
