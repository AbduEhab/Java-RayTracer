package Models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

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

        System.out.println("Outputing File of Resolution: [" + (height) + " x " + height + "]");

        String ppmStartString = "P3\n" + width + " " + height + "\n255\n";

        Long startTime = System.nanoTime();

        try {
            BufferedWriter buffer = new BufferedWriter(new FileWriter("./Renders/" + "ExampleRender.ppm"));

            buffer.write(ppmStartString);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Color c = pixel[j][i];
                    buffer.write(c.red() + " " + c.green() + " " + c.blue() + " ");
                }
                buffer.write("\n");
            }
            buffer.flush();
            buffer.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error while writing file.");
            return false;
        }

        Long endTime = System.nanoTime();
        System.out.println("File Output done in: " + (endTime - startTime) / 1000000 + "ms");
        return true;
    }

    public boolean toPNG() {

        System.out.println("Outputing File of Resolution: [" + (height) + " x " + height + "]");

        Long startTime = System.nanoTime();

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = (WritableRaster) image.getData();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                raster.setPixel(j, i, pixel[j][i].getColorArray());
            }
        }

        image.setData(raster);

        try {

            File f = new File("./Renders/" + "ExampleRender.png");

            ImageIO.write(image, "png", f);

        } catch (IOException e) {
            System.out.println("An error while writing file.");
            e.printStackTrace();
        }

        // System.out.println(raster.getpi);

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
