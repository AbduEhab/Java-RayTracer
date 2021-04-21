package Models;

import java.util.ArrayList;

abstract public class Shape {

    private Material material = new Material();

    private Matrix transform = Matrix.IDENTITY;

    abstract public ArrayList<Intersection> intersects(Ray ray);

    abstract public Vector normalAt(Point p);

    abstract public boolean equals(Shape s);

    abstract public String toString();

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
