package Models;

import java.util.ArrayList;

abstract public class Shape {

    public Material material = new Material();

    abstract public ArrayList<Intersection> intersects(Ray ray);

    abstract public Vector normalAt(Point p);
}
