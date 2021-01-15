package Models;

public class Color extends Tuple {

    public Color(double red, double green, double blue) {
        super(red, green, blue, 0);
    }

    public Color add(Tuple b) {
        return new Color(red() + b.getX(), green() + b.getY(), blue() + b.getZ());
    }

    @Override
    public Color subtract(Tuple b) {
        return new Color(red() - b.getX(), green() - b.getY(), blue() - b.getZ());
    }

    public Color multiply(double factor) {
        return new Color(red() * factor, green() * factor, blue() * factor);
    }

    public Color multiply(Color b) {
        return new Color(getX() * b.getX(), getY() * b.getY(), getZ() * b.getZ());
    }

    public double red() {
        return getX();
    }

    public double green() {
        return getY();
    }

    public double blue() {
        return getZ();
    }
}
