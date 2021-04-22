package Models;

public class Material {

    private Color color = new Color(1, 1, 1);;
    private double ambient = 0.1;;
    private double diffuse = 0.9;
    private double specular = 0.9;;
    private double shininess = 200;

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

    public Material(Color color, double ambient, double diffuse, double specular, double shininess) {
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
    }

    public Color lighting(PointLight light, Point point, Vector eyevVector, Vector normalVector, boolean inShadow) {

        Color effColor = color.multiply(light.getIntensity());

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
        this.color = color;
        return this;
    }

    public double getAmbient() {
        return ambient;
    }

    public Material setAmbient(double ambient) {
        this.ambient = ambient;
        return this;
    }

    public double getDiffuse() {
        return diffuse;
    }

    public Material setDiffuse(double diffuse) {
        this.diffuse = diffuse;
        return this;
    }

    public double getShininess() {
        return shininess;
    }

    public Material setShininess(double shininess) {
        this.shininess = shininess;
        return this;
    }

    public double getSpecular() {
        return specular;
    }

    public Material setSpecular(double specular) {
        this.specular = specular;
        return this;
    }

}
