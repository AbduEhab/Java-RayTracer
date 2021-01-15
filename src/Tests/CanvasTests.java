package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import Models.Canvas;
import Models.Color;

public class CanvasTests {

    @Test
    @DisplayName("Create Canvas")
    public void createCanvas() {

        Canvas c = new Canvas(10, 20);

        assertEquals(true, c.getPixel()[0][0].equals(new Color(0, 0, 0)), "Canvas not initiated correctly");
    }

    @Test
    @DisplayName("Write Pixel")
    public void writePixel() {

        Canvas c = new Canvas(10, 20);
        c.writePixel(0, 0, new Color(1, 1, 1));

        assertEquals(true, c.getPixel()[0][0].equals(new Color(1, 1, 1)), "Canvas not initiated correctly");
    }
}
