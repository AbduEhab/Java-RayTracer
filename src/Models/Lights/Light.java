package Models.Lights;

import Models.Tuples.Color;
import Models.Tuples.Point;

abstract public class Light {
    private Color intensity;
    private Point position;

    public Light() {
        intensity = new Color(255, 255, 255);
        position = new Point();
    }

    public Light(Color intensity, Point position) {
        this.intensity = intensity;
        this.position = position;
    }

    abstract public boolean equals(Light p);

    public Color getIntensity() {
        return intensity;
    }

    public void setIntensity(Color intensity) {
        this.intensity = intensity;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public String toString() {
        return "[ Intensity: " + intensity + ", Position: " + position + " ]";
    }
}
