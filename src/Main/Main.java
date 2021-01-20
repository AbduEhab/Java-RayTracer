package Main;

import Models.Canvas;
import Models.Color;
import Models.Enviroment;
import Models.Matrix;
import Models.Point;
import Models.Projectile;
import Models.Vector;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Started pixel calculation");
        Long startTime = System.nanoTime();

        // var c = ProjectilePath();

        var c = hourClock();

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
}
