package Models.Shapes;

import java.util.ArrayList;

import Models.Intersection;
import Models.Ray;
import Models.Tuples.Point;
import Models.Tuples.Vector;

public class XZPlane extends Shape {

    @Override
    public ArrayList<Intersection> intersects(Ray ray) {
        ray = ray.transform(getTransform().inverse());

        if (Math.abs(ray.getDirection().getY()) < 0.0001) {
            return null;
        }

        double t = -(ray.getOrigin().getY()) / (ray.getDirection().getY());
        ArrayList<Intersection> intersects = new ArrayList<Intersection>();

        
        intersects.add(new Intersection(t, this));

        return intersects;
    }

    @Override
    public Vector normalAt(Point p) {
        return new Vector(0, 1, 0);
    }

    @Override
    public boolean equals(Shape s) {
        if (!(s instanceof XZPlane))
            return false;

        if (s.hashCode() == this.hashCode())
            return true;

        return false;
    }

}
