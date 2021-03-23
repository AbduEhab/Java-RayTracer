package Models;

public class Material {

    Color color;
    double ambient;
    double diffuse;
    double specular;
    double shininess;

    public Material() {
        color = new Color(1, 1, 1);
        ambient = 0.1;
        diffuse = 0.9;
        specular = 0.9;
        shininess = 200;
    }

    public Material(Color color, double ambient, double diffuse, double specular, double shininess) {
        this.color = color;
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.specular = specular;
        this.shininess = shininess;
    }

    public Color lighting(PointLight light, Point point, Vector eyevVector, Vector normalVector) {

        Color effColor = color.multiply(light.intensity);

        Vector lightVector = light.position.subtract(point).normalize();

        Color resAmbient = color.multiply(ambient);

        double lightDotNormal = lightVector.dot(normalVector);

        Color resDiffuse;
        Color resSpecular;

        if (lightDotNormal < 0) {

            resDiffuse = new Color(0, 0, 0);
            resSpecular = new Color(0, 0, 0);

        } else {
            resDiffuse = effColor.multiply(diffuse * lightDotNormal);

            Vector reflectVector = lightVector.multiply(-1).reflect(normalVector);
            double reflectDotEye = reflectVector.dot(eyevVector);

            if (reflectDotEye <= 0)

                resSpecular = new Color(0, 0, 0);

            else {
                double factor = Math.pow(reflectDotEye, shininess);
                resSpecular = light.intensity.multiply(specular * factor);
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

    public void setColor(Color color) {
        this.color = color;
    }

    public double getAmbient() {
        return ambient;
    }

    public void setAmbient(double ambient) {
        this.ambient = ambient;
    }

    public double getDiffuse() {
        return diffuse;
    }

    public void setDiffuse(double diffuse) {
        this.diffuse = diffuse;
    }

    public double getShininess() {
        return shininess;
    }

    public void setShininess(double shininess) {
        this.shininess = shininess;
    }

    public double getSpecular() {
        return specular;
    }

    public void setSpecular(double specular) {
        this.specular = specular;
    }

}
