package Models;

public class PointLight {

    private Color intensity;
    private Point position;

    public PointLight() {
        intensity = new Color(255, 255, 255);
        position = new Point();
    }

    public PointLight(Color intensity, Point position) {
        this.intensity = intensity;
        setIntensity(intensity);

        this.position = position;
    }

    public boolean equals(PointLight p) {

        return (intensity.equals(p.intensity)) && (position.equals(p.position));
    }

    public Color getIntensity() {
        return intensity;
    }

    public void setIntensity(Color intensity) {

        this.intensity.setX(intensity.getX() * 255);
        this.intensity.setY(intensity.getY() * 255);
        this.intensity.setZ(intensity.getZ() * 255);
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
