package Models.Lights;

import Models.Tuples.Color;
import Models.Tuples.Point;

public class PointLight extends Light {

    public PointLight() {
        super();
    }

    public PointLight(Color intensity, Point position) {
        super(intensity, position);
    }

    public boolean equals(Light p) {

        return (p instanceof Light) && (getIntensity().equals(p.getIntensity()))
                && (getPosition().equals(p.getPosition()));
    }

}
