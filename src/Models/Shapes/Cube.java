package Models.Shapes;

import java.util.ArrayList;
import java.util.Arrays;

import Models.Intersection;
import Models.Ray;
import Models.Tuples.Point;
import Models.Tuples.Vector;

public class Cube extends Shape {

    @Override
    public ArrayList<Intersection> intersects(Ray ray) {

        double[] xt = checkAxix(ray.getOrigin().getX(), ray.getDirection().getX());
        double[] yt = checkAxix(ray.getOrigin().getY(), ray.getDirection().getY());
        double[] zt = checkAxix(ray.getOrigin().getZ(), ray.getDirection().getZ());

        double tmin = Math.max(xt[0], Math.max(yt[0], zt[0]));
        double tmax = Math.min(xt[1], Math.min(yt[1], zt[1]));

        if (tmin > tmax)
            return null;

        return new ArrayList<Intersection>(
                Arrays.asList(new Intersection[] { new Intersection(tmin, this), new Intersection(tmax, this) }));
    }

    private double[] checkAxix(double origin, double direction) {

        double tmin = 0;
        double tmax = 0;

        if (Math.abs(direction) >= 0.0001) {
            tmin = (-1 - origin) / direction;
            tmax = (1 - origin) / direction;
        } else {
            tmin = (-1 - origin) > 0 ? Double.MAX_VALUE : Double.MIN_VALUE;
            tmax = (1 - origin) > 0 ? Double.MAX_VALUE : Double.MIN_VALUE;
        }

        if (tmin > tmax) {
            double temp = tmin;
            tmin = tmax;
            tmax = temp;
        }

        return new double[] { tmin, tmax };
    }

    @Override
    public Vector normalAt(Point p) {

        double maxP = Math.max(Math.abs(p.getX()), Math.max(Math.abs(p.getY()), Math.abs(p.getZ())));

        if (maxP == Math.abs(p.getX()))
            return new Vector(p.getX(), 0, 0);
        else if (maxP == Math.abs(p.getY()))
            return new Vector(0, p.getY(), 0);

        return new Vector(0, 0, p.getZ());
    }

    @Override
    public boolean equals(Shape s) {
        if (!(s instanceof Cube))
            return false;

        if (getMaterial().equals(s.getMaterial()) && getTransform().equals(s.getTransform()))
            return true;

        return false;
    }

}
