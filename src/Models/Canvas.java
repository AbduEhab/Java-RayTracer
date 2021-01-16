package Models;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
        int fileNumber = 1;
        File output;
        // try {
        // output = new File("./Renders/ExampleSample.ppm");
        // if (output.createNewFile())
        // System.out.println("File created: " + output.getName());
        // else {
        // while (true) {

        // output = new File("./Renders/ExampleSample_" + fileNumber + ".ppm");
        // if (output.createNewFile()) {
        // System.out.println("File created: " + output.getName());
        // break;
        // } else {
        // fileNumber++;
        // continue;
        // }
        // }
        // }
        // } catch (IOException e) {
        // System.out.println("An error while creating file.");
        // e.printStackTrace();
        // return false;
        // }

        String ppmString = "P3\n" + getWidth() + " " + getHeight() + "\n255\n";

        for (int i = height - 1; i >= 0; i--) {
            for (int j = 0; j < width; j++) {
                Color c = pixel[j][i];
                ppmString += c.red() + " " + c.green() + " " + c.blue() + " ";
                System.out.println("Rendering pixel: [" + j + ", " + i + "]");
            }
            ppmString += "\n";
        }

        try {
            FileWriter myWriter = new FileWriter("./Renders/" + "ExampleRender.ppm");
            myWriter.write(ppmString);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error while writing file.");
            e.printStackTrace();
            return false;
        }
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
