package Models.Shapes;

import java.util.ArrayList;

import Models.Intersection;
import Models.Material;
import Models.Matrix;
import Models.Ray;
import Models.Tuples.Point;
import Models.Tuples.Vector;

abstract public class Shape {

    private Material material = new Material();

    private Matrix transform = Matrix.IDENTITY;

    abstract public ArrayList<Intersection> intersects(Ray ray);

    abstract public Vector normalAt(Point p);

    abstract public boolean equals(Shape s);

    public String toString() {
        return getClass().getSimpleName() + " " + hashCode();
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Matrix getTransform() {
        return transform;
    }

    public void setTransform(Matrix transform) {
        this.transform = transform;
    }
}
