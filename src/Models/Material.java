package Models;

import Models.Lights.Light;
import Models.Patterns.Pattern;
import Models.Shapes.Shape;
import Models.Tuples.Color;
import Models.Tuples.Point;
import Models.Tuples.Vector;

public class Material {

    private Color color = new Color(1, 1, 1);;
    private Pattern pattern;
    private double ambient = 0.1;;
    private double diffuse = 0.9;
    private double specular = 0.9;;
    private double shininess = 200;
    private double reflectiveness = 0;
    private double transparency = 0;
    private double refractiveIndex = 1;

    public Material() {
        color = new Color(1, 1, 1);
        ambient = 0.1;
        diffuse = 0.9;
        specular = 0.9;
        shininess = 200;
    }

    public String toString() {
        return "[ Color: " + color + ", Ambient" + ambient + ", Diffuse: " + diffuse + ", Specular: " + specular
                + ", Shininess: " + shininess + " ]";
    }

    public Material(Color color, double ambient, double diffuse, double specular, double shininess, Pattern pattern,
            double reflectiveness, double transparency, double refractiveIndex) {
        if (color != null)
            this.color = color;
        if (ambient >= 0)
            this.ambient = ambient;
        if (diffuse >= 0)
            this.diffuse = diffuse;
        if (specular >= 0)
            this.specular = specular;
        if (shininess >= 0)
            this.shininess = shininess;

        this.pattern = pattern;

        this.reflectiveness = reflectiveness > 1 ? 1 : reflectiveness < 0 ? 0 : reflectiveness;

        this.transparency = transparency >= 0 ? transparency : 0;

        this.refractiveIndex = refractiveIndex >= 0 ? refractiveIndex : 0;
    }

    public Color lighting(Light light, Shape shape, Point point, Vector eyevVector, Vector normalVector,
            boolean inShadow) {

        Color effColor = null;
        if (pattern != null) {
            effColor = pattern.colorAt(shape, point).multiply(light.getIntensity());

        } else
            effColor = color.multiply(light.getIntensity());

        Vector lightVector = light.getPosition().subtract(point).normalize();

        Color resAmbient = effColor.multiply(ambient);

        double lightDotNormal = lightVector.dot(normalVector);

        Color resDiffuse = new Color(0, 0, 0);
        Color resSpecular = new Color(0, 0, 0);

        if (!inShadow && lightDotNormal >= 0) {

            resDiffuse = effColor.multiply(diffuse * lightDotNormal);

            Vector reflectVector = lightVector.multiply(-1).reflect(normalVector);
            double reflectDotEye = reflectVector.dot(eyevVector);

            if (reflectDotEye <= 0)

                resSpecular = new Color(0, 0, 0);

            else {
                double factor = Math.pow(reflectDotEye, shininess);
                resSpecular = light.getIntensity().multiply(specular * factor);
            }
        }

        return resAmbient.add(resDiffuse).add(resSpecular);
    }

    public boolean equals(Material b) {
        return color.equals(b.color) && ambient == b.ambient && specular == b.specular && shininess == b.shininess;
    }

    public Color getColor() {
        return color;
    }

    public Material setColor(Color color) {
        if (color != null)
            this.color = color;
        return this;
    }

    public double getAmbient() {
        return ambient;
    }

    public Material setAmbient(double ambient) {
        if (ambient >= 0)
            this.ambient = ambient;
        return this;
    }

    public double getDiffuse() {
        return diffuse;
    }

    public Material setDiffuse(double diffuse) {
        if (diffuse >= 0)
            this.diffuse = diffuse;

        return this;
    }

    public double getShininess() {
        return shininess;
    }

    public Material setShininess(double shininess) {
        if (shininess >= 0)
            this.shininess = shininess;
        return this;
    }

    public double getSpecular() {
        return specular;
    }

    public Material setSpecular(double specular) {
        if (specular >= 0)
            this.specular = specular;
        return this;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public Material setPattern(Pattern pattern) {
        this.pattern = pattern;
        return this;
    }

    public double getReflectiveness() {
        return reflectiveness;
    }

    public Material setReflectiveness(double reflectiveness) {
        this.reflectiveness = reflectiveness > 1 ? 1 : reflectiveness < 0 ? 0 : reflectiveness;
        return this;
    }

    public double getTransparency() {
        return transparency;
    }

    public Material setTransparency(double transparency) {
        if (transparency >= 0)
            this.transparency = transparency;
        return this;
    }

    public double getRefractiveIndex() {
        return refractiveIndex;
    }

    public Material setRefractiveIndex(double refractiveIndex) {
        if (refractiveIndex >= 0)
            this.refractiveIndex = refractiveIndex;

        return this;
    }

}
