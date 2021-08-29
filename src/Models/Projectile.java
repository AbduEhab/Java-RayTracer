package Models;

import Models.Tuples.Point;
import Models.Tuples.Vector;

public class Projectile {
    private Point position;
    private Vector velocity;

    public Projectile(Point position, Vector velocity) {
        if (position != null)
            this.position = position;
        else
            this.position = new Point();
        if (velocity != null)
            this.velocity = velocity;
        else
            this.velocity = new Vector();
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        if (position != null)
            this.position = position;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector velocity) {
        if (velocity != null)
            this.velocity = velocity;
    }

}
