package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import Models.Canvas;
import Models.Tuples.Color;

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

    @Test
    @DisplayName("Write PPM File")
    public void writePpmFile() {

        try {
            FileWriter myWriter = new FileWriter("test.ppm");
            myWriter.write("P3\n2\n2\n255\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            File myObj = new File("test.ppm");
            Scanner myReader = new Scanner(myObj);

            assertEquals("P3", myReader.nextLine(), "PPM file incorrectly written");
            assertEquals("2", myReader.nextLine(), "PPM file incorrectly written");
            assertEquals("2", myReader.nextLine(), "PPM file incorrectly written");
            assertEquals("255", myReader.nextLine(), "PPM file incorrectly written");

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        File myObj = new File("test.ppm");
        if (myObj.delete()) {
            System.out.println("Deleted the file: " + myObj.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }
}
