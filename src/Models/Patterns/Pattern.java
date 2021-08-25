package Models.Patterns;

import Models.Matrix;
import Models.Shapes.Shape;
import Models.Tuples.Color;
import Models.Tuples.Point;

abstract public class Pattern {

    private Matrix transform = Matrix.IDENTITY;

    private Color firstColor = Color.WHITE;
    private Color secondColor = Color.BLACK;

    public Pattern() {
        firstColor = Color.WHITE;
        secondColor = Color.BLACK;
    }

    public Pattern(Color[] colors) {

        if (colors.length != 2) {
            firstColor = Color.WHITE;
            secondColor = Color.BLACK;
            return;
        }

        firstColor = colors[0];
        secondColor = colors[1];

    }

    abstract public Color colorAt(Point p);

    public Color colorAt(Shape s, Point p) {

        Point objectP = s.getTransform().inverse().multiply(p);
        Point patternP = getTransform().inverse().multiply(objectP);

        return colorAt(patternP);
    }

    public Color getFirstColor() {
        return firstColor;
    }

    public Pattern setFirstColor(Color firstColor) {
        this.firstColor = firstColor;
        return this;
    }

    public Color getSecondColor() {
        return secondColor;
    }

    public Pattern setSecondColor(Color secondColor) {
        this.secondColor = secondColor;
        return this;
    }

    public Matrix getTransform() {
        return transform;
    }

    public Pattern setTransform(Matrix transform) {
        this.transform = transform;
        return this;
    }

}
