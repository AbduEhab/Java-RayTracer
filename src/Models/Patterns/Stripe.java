package Models.Patterns;

import Models.Tuples.Color;
import Models.Tuples.Point;

public class Stripe extends Pattern {

    public Stripe(Color[] colors) {
        super(colors);
    }

    public Color stripeAt(Point p) {

        int arraySize = getColors().size();

        // if (arraySize != 0)
        // for (Color color : getColors()) {
        // if ((Math.floor(p.getX()) % arraySize) == 0)
        // return color;

        // }

        for (int i = 1; i <= arraySize; i++) {
            int flooredNumber = (int) Math.floor(p.getX());

            double b = flooredNumber % (i + 0.0);

            if ((b) == 0.0)
                return getColors().get(i - 1);
        }
        return null;
    }

}
