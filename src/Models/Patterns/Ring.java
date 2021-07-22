package Models.Patterns;

import Models.Tuples.Color;
import Models.Tuples.Point;

public class Ring extends Pattern {

    public Ring() {
        super();
    }

    public Ring(Color[] colors) {
        super(colors);
    }

    public Color colorAt(Point p) {

        if ((int) Math.sqrt(p.getX() * p.getX() + p.getZ() * p.getZ()) % 2 == 0)
            return getFirstColor();
        else
            return getSecondColor();
    }
}
