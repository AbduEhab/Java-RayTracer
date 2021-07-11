package Models.Tuples;

public class Color extends Tuple {

    public static final Color BLACK = new Color(0, 0, 0);
    public static final Color WHITE = new Color(1, 1, 1);

    public static final Color RED = new Color(1, 0, 0);
    public static final Color GREEN = new Color(0, 1, 0);
    public static final Color BLUE = new Color(0, 0, 1);

    public static final Color PURPLE = new Color(1, 0, 1);
    public static final Color YELLOW = new Color(1, 1, 0);

    public Color(double red, double green, double blue) {

        super(red > 255 ? 255 : red < 0 ? 0 : red, green > 255 ? 255 : green < 0 ? 0 : green,
                blue > 255 ? 255 : blue < 0 ? 0 : blue, 0);
    }

    public Color add(Tuple b) {
        return new Color(getX() + b.getX(), getY() + b.getY(), getZ() + b.getZ());
    }

    public Color subtract(Tuple b) {
        return new Color(getX() - b.getX(), getY() - b.getY(), getZ() - b.getZ());
    }

    public Color multiply(double factor) {
        return new Color(getX() * factor, getY() * factor, getZ() * factor);
    }

    public Color multiply(Color b) {
        return new Color(getX() * b.getX(), getY() * b.getY(), getZ() * b.getZ());
    }

    public int red() {
        return (int) getX();
    }

    public int green() {
        return (int) getY();
    }

    public int blue() {
        return (int) getZ();
    }

    public String toString() {
        return "( " + red() + ", " + green() + ", " + blue() + ")";
    }
}
