package Models;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import Models.Tuples.Color;

public class Canvas {
    private int width;
    private int height;

    private Color[][] pixel;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;

        pixel = new Color[width][height];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                pixel[j][i] = new Color(0, 0, 0);
            }
        }
    }

    public Canvas writePixel(int x, int y, Color color) {

        if (!(x < 0 || y < 0))
            pixel[x][y] = color;
        return this;
    }

    public boolean toPPM() {
        return toPPM("./Renders/" + "ExampleRender.ppm");
    }

    public boolean toPPM(String path) {

        System.out.println("Outputing File of Resolution: [" + (width) + " x " + height + "]");

        String ppmStartString = "P3\n" + width + " " + height + "\n255\n";

        Long startTime = System.nanoTime();

        try {

            BufferedWriter buffer = new BufferedWriter(new FileWriter(path));

            buffer.write(ppmStartString);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Color c = pixel[j][i];
                    buffer.write(c.red() + " " + c.green() + " " + c.blue() + " ");
                }
                buffer.write("\n");
            }
            buffer.close();

        } catch (FileNotFoundException e) {

            try {
                Path renderPath = Paths.get("../Renders/");
                Files.createDirectories(renderPath);
                toPPM("../Renders/" + "ExampleRender.ppm");

            } catch (IOException e1) {
                System.out.println("An unknown error occured while creating file.");
                e1.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An unknown error occured while writing file.");
            return false;
        }

        Long endTime = System.nanoTime();
        System.out.println("File Output done in: " + (endTime - startTime) / 1000000 + "ms");
        return true;

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color[][] getPixel() {
        return pixel;
    }
}
