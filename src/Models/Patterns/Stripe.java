package Models.Patterns;

import Models.Tuples.Color;
import Models.Tuples.Point;

public class Stripe extends Pattern {

    public Stripe() {
        super();
    }

    public Stripe(Color[] colors) {
        super(colors);
    }

    public Color colorAt(Point p) {

        if (Math.floor(p.getX()) % 2 == 0)
            return getFirstColor();
        else
            return getSecondColor();

    }

}
