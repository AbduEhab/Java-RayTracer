package Main;

import Models.Canvas;
import Models.Color;
import Models.Enviroment;
import Models.Matrix;
import Models.Point;
import Models.Projectile;
import Models.Ray;
import Models.Sphere;
import Models.Vector;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Started pixel calculation");
        Long startTime = System.nanoTime();

        // var c = ProjectilePath();

        // var c = hourClock();

        var c = circle();

        Long endTime = System.nanoTime();
        System.out.println("Pixel Calculation done in: " + (endTime - startTime) / 1000000 + "ms");

        c.toPPM();
    }

    private static Canvas ProjectilePath() { // Program 1

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

    private static Canvas hourClock() { // Program 2
        var c = new Canvas(600, 600);

        Point hourPoint = new Point(0, 3 * 600 / 8, 0);

        // hourPoint = Matrix.Identity.translate(0, 200, 0).multiply(hourPoint);

        for (int i = 0; i < 12; i++) {

            c.writePixel((int) hourPoint.getX() + 300, (int) hourPoint.getY() + 300, new Color(255, 255, 255));
            hourPoint = Matrix.IDENTITY.rotateZ(Math.PI / 6).multiply(hourPoint);
        }
        return c;
    }

    private static Canvas circle() {// program 2
        var c = new Canvas(100, 100);

        Color red = new Color(255, 0, 0);

        Sphere s = new Sphere();

        int wallSize = 7;
        double pixelSize = wallSize / (c.getHeight() + 0.0);

        double half = wallSize / 2.0;

        for (int i = 0; i < c.getHeight(); i++) {
            int worldY = (int) ((-c.getHeight() / 2) + i);
            System.out.println(worldY);

            for (int j = 0; j < c.getWidth(); j++) {
                int worldX = (int) (-(c.getHeight() / 2) + j);

                Point position = new Point(worldX, worldY, 10);

                Ray ray = new Ray(new Point(0, 0, -5), position.subtract(new Point(0, 0, -5)).normalize());

                var x = ray.intersects(s);

                if (x != null) {
                    c.writePixel(i, j, red);
                }
            }
        }

        return c;
    }
}
