package Models.Patterns;

import Models.Tuples.Color;
import Models.Tuples.Point;

public class Gradient extends Pattern {

    public Gradient() {
        super();
    }

    public Gradient(Color[] colors) {
        super(colors);
    }

    public Color colorAt(Point p) {
        Color distance = getSecondColor().subtract(getFirstColor());
        double fraction = p.getX() - Math.floor(p.getX());

        return getFirstColor().add(distance.multiply(fraction));
    }

}
