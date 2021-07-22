package Models.Patterns;

import Models.Tuples.Color;
import Models.Tuples.Point;

public class Checker extends Pattern {

    public Checker() {
        super();
    }

    public Checker(Color[] colors) {
        super(colors);
    }

    public Color colorAt(Point p) {

        if ((Math.abs((int) p.getX()) + Math.abs((int) p.getY()) + Math.abs((int) p.getZ())) % 2 == 0)
            return getFirstColor();
        else
            return getSecondColor();
    }
}