package Models;

public class Projectile {
    private Point position;
    private Vector velocity;

    public Projectile(Point position, Vector velocity) {
        this.position = position;
        this.velocity = velocity;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

}
