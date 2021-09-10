package Main;

import Models.Camera;
import Models.Canvas;
import Models.Tuples.Color;
import Models.Enviroment;
import Models.Material;
import Models.Matrix;
import Models.Projectile;
import Models.Ray;
import Models.Shapes.XZPlane;
import Models.Shapes.Shape;
import Models.Shapes.Sphere;
import Models.World;
import Models.Lights.PointLight;
import Models.Patterns.Checker;
import Models.Patterns.Gradient;
import Models.Patterns.Ring;
import Models.Patterns.Stripe;
import Models.Tuples.Point;
import Models.Tuples.Vector;

public class Main {
    public static void main(String[] args) {
        System.out.println("Started pixel calculation");
        Long startTime = System.nanoTime();

        // var c = ProjectilePath();

        // var c = hourClock();

        // var c = circle(100);

        // var c = sphere(300);

        // var c = shadowWorld1();

        // var c = shadowWorld2();

        // var c = patternWorld();

        // var c = reflectiveWorld();

        var c = refractiveWorld();

        Long endTime = System.nanoTime();
        System.out.println("Pixel Calculation done in: " + (endTime - startTime) / 1000000 + "ms");

        c.toPNG();

        endTime = System.nanoTime();
        System.out.println("Rendering done in: " + (endTime - startTime) / 1000000 + "ms");

    }

    private static Canvas ProjectilePath() { // Program 1 --Chapter 2

        Projectile ball = new Projectile(new Point(0, 1, 0), new Vector(1, 1.8, 0).normalize().multiply(11.25));
        Enviroment atm = new Enviroment(new Vector(0, -0.1, 0), new Vector(-0.01, 0, 0));
        var c = new Canvas(900, 550);

        while (ball.getPosition().getY() > 0) {

            c.writePixel((int) ball.getPosition().getX(), (int) ball.getPosition().getY(), new Color(255, 255, 255));
            ball.setPosition(ball.getPosition().add(ball.getVelocity()));
            ball.setVelocity(ball.getVelocity().add(atm.getGravity().add(atm.getWind())));

        }
        ball.getPosition().setY(0);
        return c;
    }

    private static Canvas hourClock() { // Program 2 --Chapter 4
        var c = new Canvas(600, 600);

        Point hourPoint = new Point(0, 3 * 600 / 8, 0);

        // hourPoint = Matrix.Identity.translate(0, 200, 0).multiply(hourPoint);

        for (int i = 0; i < 12; i++) {

            c.writePixel((int) hourPoint.getX() + 300, (int) hourPoint.getY() + 300, new Color(255, 255, 255));
            hourPoint = Matrix.IDENTITY.rotateZ(Math.PI / 6).multiply(hourPoint);
        }
        return c;
    }

    private static Canvas circle(int size) { // program 3 --Chapter 5
        var c = new Canvas(size, size);

        Color red = new Color(255, 0, 0);

        Models.Shapes.Sphere s = new Sphere();

        int wallSize = 7;
        double pixelSize = wallSize / (c.getHeight() + 0.0);

        double half = wallSize / 2.0;

        for (int i = 0; i < c.getHeight(); i++) {
            double worldY = ((half) - (i * pixelSize));

            System.out.println("Calculating Row: [" + i + '/' + c.getHeight() + ']');

            for (int j = 0; j < c.getWidth(); j++) {
                double worldX = ((-half) + (j * pixelSize));

                Point wall = new Point(worldX, worldY, 10);

                // Point wall = new Point(i-100, j, 0);

                Ray ray = new Ray(new Point(0, 0, -5), wall.subtract(new Point(0, 0, -5)).normalize());

                if (ray.intersects(s) != null) {
                    c.writePixel(i, j, red);
                }
            }
        }

        return c;
    }

    private static Canvas sphere(int size) { // program 4 --Chapter 6
        var c = new Canvas(size, size);

        Sphere s = new Sphere();

        Material m = new Material();
        m.setColor(new Color(1, 0.2, 1));

        s.setMaterial(m);

        PointLight l = new PointLight(new Color(255, 255, 255), new Point(-10, 10, -10));

        int wallSize = 7;
        double pixelSize = wallSize / (c.getHeight() + 0.0);

        double half = wallSize / 2.0;

        for (int i = 0; i < c.getHeight(); i++) {
            double worldY = ((half) - (i * pixelSize));

            System.out.println("Calculating Row: [" + i + '/' + c.getHeight() + ']');

            for (int j = 0; j < c.getWidth(); j++) {
                double worldX = ((-half) + (j * pixelSize));

                Point wall = new Point(worldX, worldY, 10);

                Ray ray = new Ray(new Point(0, 0, -5), wall.subtract(new Point(0, 0, -5)).normalize());

                var intersections = ray.intersects(s);

                if (intersections != null) {
                    Point hitPoint = ray.position(intersections.get(0).getT());
                    Vector normalVector = intersections.get(0).getShape().normalAt(hitPoint);
                    Vector eyeVector = ray.getDirection().multiply(-1);

                    Color color = intersections.get(0).getShape().getMaterial().lighting(l, s, hitPoint, eyeVector,
                            normalVector, false);

                    c.writePixel(i, j, color);
                }
            }
        }

        return c;
    }

    private static Canvas shadowWorld1() { // program 5 --Chapter 7-8

        // Note(AbduEhab): Something is wrong with the rendering function that breaks my
        // CPU's interrupt routines. Don't know what is happening but this isn't a
        // problem
        // if you render small images so I wont pay attention to it atm.

        Sphere floor = new Sphere();
        floor.setTransform(Matrix.IDENTITY.scale(10, 0.01, 10));
        floor.getMaterial().setColor(new Color(1, 0.9, 0.9)).setSpecular(0);

        Sphere leftWall = new Sphere();
        leftWall.setTransform(
                Matrix.IDENTITY.translate(0, 0, 5).rotateY(-Math.PI / 4).rotateX(Math.PI / 2).scale(10, 0.01, 10));
        leftWall.setMaterial(floor.getMaterial());

        Sphere rightWall = new Sphere();
        rightWall.setTransform(
                Matrix.IDENTITY.translate(0, 0, 5).rotateY(Math.PI / 4).rotateX(Math.PI / 2).scale(10, 0.01, 10));
        rightWall.setMaterial(floor.getMaterial());

        Sphere middleSphere = new Sphere();
        middleSphere.setTransform(Matrix.IDENTITY.translate(-0.5, 1, 0.5));
        middleSphere.getMaterial().setColor(new Color(0.1, 1, 0.5)).setDiffuse(0.7).setSpecular(0.3);

        Sphere rightSphere = new Sphere();
        rightSphere.setTransform(Matrix.IDENTITY.translate(1.5, 0.5, -0.5).scale(0.5, 0.5, 0.5));
        rightSphere.getMaterial().setColor(new Color(0.5, 1, 0.1)).setDiffuse(0.7).setSpecular(0.3);

        Sphere leftSphere = new Sphere();
        leftSphere.setTransform(Matrix.IDENTITY.translate(-1.5, 0.33, -0.75).scale(0.33, 0.33, 0.33));
        leftSphere.getMaterial().setColor(new Color(1, 0.8, 0.1)).setDiffuse(0.7).setSpecular(0.3);

        World w = new World();

        w.addShape(new Shape[] { leftSphere, rightSphere, middleSphere, floor, leftWall, rightWall });

        w.addLight(new PointLight(new Color(255, 255, 255), new Point(-10, 10, -10)));

        Camera c = new Camera(200, 160, Math.PI / 3);
        c.transform(new Point(0, 1.5, -5), new Point(0, 1, 0), new Vector(0, 1, 0));

        return c.renderMultiThread(w, -1);
    }

    private static Canvas shadowWorld2() { // program 6 --Chapter 9

        Shape floor = new XZPlane();
        floor.getMaterial().setColor(new Color(1, 0.9, 0.9)).setSpecular(0);

        Sphere middleSphere = new Sphere();
        middleSphere.setTransform(Matrix.IDENTITY.translate(-0.5, 1, 0.5));
        middleSphere.getMaterial().setColor(new Color(0.1, 1, 0.5)).setDiffuse(0.7).setSpecular(0.3);

        Sphere rightSphere = new Sphere();
        rightSphere.setTransform(Matrix.IDENTITY.translate(1.5, 0.5, -0.5).scale(0.5, 0.5, 0.5));
        rightSphere.getMaterial().setColor(new Color(0.5, 1, 0.1)).setDiffuse(0.7).setSpecular(0.3);

        Sphere leftSphere = new Sphere();
        leftSphere.setTransform(Matrix.IDENTITY.translate(-1.5, 0.33, -0.75).scale(0.33, 0.33, 0.33));
        leftSphere.getMaterial().setColor(new Color(1, 0.8, 0.1)).setDiffuse(0.7).setSpecular(0.3);

        World w = new World();

        w.addShape(new Shape[] { floor, leftSphere, rightSphere, middleSphere });

        w.addLight(new PointLight(new Color(255, 255, 255), new Point(-10, 10, -10)));

        Camera c = new Camera(160, 120, Math.PI / 3);
        c.transform(new Point(0, 1.5, -5), new Point(0, 1, 0), new Vector(0, 1, 0));

        return c.renderMultiThread(w, -1);
    }

    private static Canvas patternWorld() { // program 7 --Chapter 10

        Shape floor = new XZPlane();
        floor.getMaterial().setColor(new Color(1, 0.9, 0.9)).setSpecular(0)
                .setPattern(new Checker(new Color[] { Color.WHITE, Color.BLACK }));

        Sphere middleSphere = new Sphere();
        middleSphere.setTransform(Matrix.IDENTITY.translate(-0.5, 1, 0.5));
        middleSphere.getMaterial().setColor(new Color(0.1, 1, 0.5)).setDiffuse(0.7).setSpecular(0.3)
                .setPattern(new Gradient(new Color[] { Color.BLUE, Color.PURPLE })
                        .setTransform(Matrix.IDENTITY.scale(2, 2, 2).translate(0.5, 0.5, 0.5)));

        Sphere rightSphere = new Sphere();
        rightSphere.setTransform(Matrix.IDENTITY.translate(1.5, 0.5, -0.5).scale(0.5, 0.5, 0.5));
        rightSphere.getMaterial().setColor(new Color(0.5, 1, 0.1)).setDiffuse(0.7).setSpecular(0.3)
                .setPattern(new Ring(new Color[] { Color.RED, Color.WHITE })
                        .setTransform(Matrix.IDENTITY.scale(0.15, 0.15, 0.15).rotateZ(1)));

        Sphere leftSphere = new Sphere();
        leftSphere.setTransform(Matrix.IDENTITY.translate(-1.5, 0.33, -0.75).scale(0.33, 0.33, 0.33));
        leftSphere.getMaterial().setColor(new Color(1, 0.8, 0.1)).setDiffuse(0.7).setSpecular(0.3)
                .setPattern(new Stripe(new Color[] { Color.GREEN, Color.DARK_GREY }));

        World w = new World();

        w.addShape(new Shape[] { floor, leftSphere, rightSphere, middleSphere });

        w.addLight(new PointLight(new Color(255, 255, 255), new Point(-10, 10, -10)));

        Camera c = new Camera(200, 160, Math.PI / 2.8);

        c.transform(new Point(0, 1.5, -5), new Point(0, 1, 0), new Vector(0, 1, 0));

        return c.renderMultiThread(w, -1);
    }

    private static Canvas reflectiveWorld() { // program 8 --Chapter 11

        Shape floor = new XZPlane();
        floor.getMaterial().setColor(new Color(1, 0.9, 0.9)).setSpecular(0).setReflectiveness(0.3);

        Sphere middleSphere = new Sphere();
        middleSphere.setTransform(Matrix.IDENTITY.translate(-0.5, 1, 0.5));
        middleSphere.getMaterial().setColor(new Color(0.1, 1, 0.5)).setDiffuse(0.7).setSpecular(0.3)
                .setReflectiveness(0.3);

        Sphere rightSphere = new Sphere();
        rightSphere.setTransform(Matrix.IDENTITY.translate(1.5, 0.5, -0.5).scale(0.5, 0.5, 0.5));
        rightSphere.getMaterial().setColor(new Color(0.5, 1, 0.1)).setDiffuse(0.7).setSpecular(0.3)
                .setReflectiveness(0.3);

        Sphere leftSphere = new Sphere();
        leftSphere.setTransform(Matrix.IDENTITY.translate(-1.5, 0.33, -0.75).scale(0.33, 0.33, 0.33));
        leftSphere.getMaterial().setColor(new Color(1, 0.8, 0.1)).setDiffuse(0.7).setSpecular(0.3)
                .setReflectiveness(0.3);

        World w = new World();

        w.addShape(new Shape[] { floor, leftSphere, rightSphere, middleSphere });

        w.addLight(new PointLight(new Color(255, 255, 255), new Point(-10, 10, -10)));

        Camera c = new Camera(200, 160, Math.PI / 3);

        c.transform(new Point(0, 1.5, -5), new Point(0, 1, 0), new Vector(0, 1, 0));

        return c.renderMultiThread(w, -1);
    }

    private static Canvas refractiveWorld() { // program 9 --Chapter 12

        // Note(AbduEhab): The transparent sphere still casts shadows which is weird.
        // I need to fix that. Prob just gonna have to do some magic with the shadow
        // detection logic.

        Shape floor = new XZPlane();
        floor.getMaterial().setColor(new Color(1, 0.9, 0.9)).setSpecular(0).setReflectiveness(0.3);

        Sphere middleSphere = new Sphere();
        middleSphere.setTransform(Matrix.IDENTITY.translate(-0.5, 1, 0.5));
        middleSphere.getMaterial().setColor(new Color(0, 0, 0)).setDiffuse(0.1).setSpecular(1).setReflectiveness(0.3)
                .setShininess(300).setTransparency(1);

        Sphere rightSphere = new Sphere();
        rightSphere.setTransform(Matrix.IDENTITY.translate(1.5, 0.5, -0.5).scale(0.5, 0.5, 0.5));
        rightSphere.getMaterial().setColor(new Color(0.5, 1, 0.1)).setDiffuse(0.7).setSpecular(0.3)
                .setReflectiveness(0.3);

        Sphere leftSphere = new Sphere();
        leftSphere.setTransform(Matrix.IDENTITY.translate(-1.5, 0.33, -0.75).scale(0.33, 0.33, 0.33));
        leftSphere.getMaterial().setColor(new Color(1, 0.8, 0.1)).setDiffuse(0.7).setSpecular(0.3)
                .setReflectiveness(0.3);

        World w = new World();

        w.addShape(new Shape[] { floor, leftSphere, rightSphere, middleSphere });

        w.addLight(new PointLight(new Color(255, 255, 255), new Point(-10, 10, -10)));

        Camera c = new Camera(1280, 720, Math.PI / 3);

        c.transform(new Point(0, 1.5, -5), new Point(0, 1, 0), new Vector(0, 1, 0));

        return c.renderMultiThread(w, -1);
    }

}