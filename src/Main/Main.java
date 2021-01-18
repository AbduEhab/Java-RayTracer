package Main;

import Models.Canvas;
import Models.Color;
import Models.Enviroment;
import Models.Point;
import Models.Projectile;
import Models.Vector;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Started pixel calculation");

        Long startTime = System.nanoTime();

        Projectile ball = new Projectile(new Point(0, 1, 0), new Vector(1, 1.8, 0).normalize().multiply(11.25));
        Enviroment atm = new Enviroment(new Vector(0, -0.1, 0), new Vector(-0.01, 0, 0));
        var c = tick(atm, ball);

        Long endTime = System.nanoTime();

        System.out.println("Pixel Calculation done in: " + (endTime - startTime)/1000000 + "ms");

        c.toPPM();
    }

    public static Canvas tick(Enviroment atm, Projectile ball) throws InterruptedException {
        var c = new Canvas(900, 550);

        while (ball.getPosition().getY() > 0) {
            // TimeUnit.SECONDS.sleep(2);
            c.writePixel((int) ball.getPosition().getX(), (int) ball.getPosition().getY(), new Color(255, 255, 255));
            ball.setPosition(ball.getPosition().add(ball.getVelocity()));
            ball.setVelocity(ball.getVelocity().add(atm.getGravity().add(atm.getWind())));

            // System.out.println("[" + ball.getPosition().getX() + ", " +
            // ball.getPosition().getY() + ", "
            // + ball.getPosition().getZ() + "]");
        }
        ball.getPosition().setY(0);
        return c;
    }
}
