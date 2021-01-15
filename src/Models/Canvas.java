package Models;

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

    public boolean writePixel(int x, int y, Color color) {

        pixel[x][y] = color;
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

    public void setPixel(Color[][] pixel) {
        this.pixel = pixel;
    }
}
