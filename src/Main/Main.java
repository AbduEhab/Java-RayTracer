package Main;

import java.util.concurrent.TimeUnit;

import Models.Enviroment;
import Models.Point;
import Models.Projectile;
import Models.Vector;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Projectile ball = new Projectile(new Point(0, 1, 0), new Vector(1, 1, 0).normalize());
        Enviroment atm = new Enviroment(new Vector(0, -0.1, 0), new Vector(-0.01, 0, 0));
        while (true) {
            TimeUnit.SECONDS.sleep(1);
            tick(atm, ball);
        }

    }

    public static void tick(Enviroment atm, Projectile ball) {
        ball.setPosition(ball.getPosition().add(ball.getVelocity()));
        ball.setVelocity(ball.getVelocity().add(atm.getGravity().add(atm.getWind())));
        System.out.println("[" + ball.getPosition().getX() + ", " + ball.getPosition().getY() + ", "
                + ball.getPosition().getZ() + "]");
    }
}
