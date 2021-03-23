package Models;

public class PointLight {

    Color intensity;
    Point position;

    public PointLight() {
        intensity = new Color(1, 1, 1);
        position = new Point();
    }

    public PointLight(Color intensity, Point position) {
        this.intensity = intensity;
        this.position = position;
    }

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

}
