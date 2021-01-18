package Models;

public class Color extends Tuple {

    public Color(double red, double green, double blue) {

        super(red > 255 ? 255 : red < 0 ? 0 : red, green > 255 ? 255 : green < 0 ? 0 : green,
                blue > 255 ? 255 : blue < 0 ? 0 : blue, 0);
    }

    public Color add(Tuple b) {
        return new Color(red() + b.getX(), green() + b.getY(), blue() + b.getZ());
    }

    public Color subtract(Tuple b) {
        return new Color(red() - b.getX(), green() - b.getY(), blue() - b.getZ());
    }

    public Color multiply(double factor) {
        return new Color(red() * factor, green() * factor, blue() * factor);
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
        return "Color is: ( " + red() + ", " + green() + ", " + blue() + ")\n";
    }
}
