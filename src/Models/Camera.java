package Models;

import java.util.ArrayList;

import javax.xml.namespace.QName;

public class Camera {
    private int hSize;
    private int vSize;
    private double fov;
    private double pixelSize;
    private Matrix transform = Matrix.IDENTITY;

    // private double halfView;
    private double halfHeight;
    private double halfWidth;

    public Camera(int hSize, int vSize, double fov) {
        this.hSize = hSize;
        this.vSize = vSize;
        this.fov = fov;

        setPixleSize();
    }

    public Matrix transform(Point from, Point to, Vector up) {
        Vector forward = to.subtract(from).normalize();

        Vector left = forward.cross(up.normalize());

        Vector trueUp = left.cross(forward);

        Matrix orientation = new Matrix(4,
                new double[][] { { left.getX(), left.getY(), left.getZ(), 0 },
                        { trueUp.getX(), trueUp.getY(), trueUp.getZ(), 0 },
                        { -forward.getX(), -forward.getY(), -forward.getZ(), 0 }, { 0, 0, 0, 1 } });

        transform = orientation.translate(-from.getX(), -from.getY(), -from.getZ());

        return transform;
    }

    public Ray rayForPixel(int x, int y) {

        double xOffset = (x + 0.5) * pixelSize;
        double yOffset = (y + 0.5) * pixelSize;

        double worldX = halfWidth - xOffset;
        double worldY = halfHeight - yOffset;

        Point pixel = transform.inverse().multiply(new Point(worldX, worldY, -1));
        Point origin = transform.inverse().multiply(new Point());
        Vector direction = pixel.subtract(origin).normalize();

        return new Ray(origin, direction);
    }

    public Canvas render(World w) throws InterruptedException { // best bit of engineering I did in a while
        Canvas c = new Canvas(hSize, vSize);

        int cores = Runtime.getRuntime().availableProcessors();
        ArrayList<Thread> threads = new ArrayList<Thread>();

        if (cores > 1)
            for (int i = 0; i < cores; i++) {

                final int index = i;

                Runnable runnable = () -> {
                    for (int y = index; y < vSize; y += cores) {

                        System.out.println(
                                ">> Thread {" + index + "}: Calculating Row: [" + y + '/' + c.getHeight() + ']');

                        for (int x = 0; x < hSize; x++) {

                            Ray ray = rayForPixel(x, y);

                            Color color = w.colorAt(ray);

                            c.writePixel(x, y, color);
                        }
                    }
                };

                threads.add(new Thread(runnable));
            }

        if (!threads.isEmpty()) {

            for (int i = 0; i < threads.size(); i++) {
                threads.get(i).start();

                if (i == cores - 1)
                    threads.get(i).join();
            }

            for (Thread thread : threads) {
                thread.join();
            }

        } else {
            for (int y = 0; y < vSize; y++) {

                System.out.println("Calculating Row: [" + y + '/' + c.getHeight() + ']');

                for (int x = 0; x < hSize; x++) {

                    Ray ray = rayForPixel(x, y);

                    Color color = w.colorAt(ray);

                    c.writePixel(x, y, color);
                }
            }
        }

        return c;
    }

    private void setPixleSize() {
        double halfView = Math.tan(fov / 2);
        double aspect = hSize / (vSize + 0.0);

        if (aspect >= 1) {
            halfWidth = halfView;
            halfHeight = halfView / aspect;
        } else {
            halfWidth = halfView / aspect;
            halfHeight = halfView;
        }

        pixelSize = halfWidth * 2.0 / hSize;
    }

    public int gethSize() {
        return hSize;
    }

    public void sethSize(int hSize) {
        this.hSize = hSize;
        setPixleSize();
    }

    public int getvSize() {
        return vSize;
    }

    public void setvSize(int vSize) {
        this.vSize = vSize;
        setPixleSize();
    }

    public double getFov() {
        return fov;
    }

    public void setFov(double fov) {
        this.fov = fov;
        setPixleSize();
    }

    public Matrix getTransform() {
        return transform;
    }

    public void setTransform(Matrix transform) {
        this.transform = transform;
    }

    public double getPixelSize() {
        return pixelSize;
    }

    public void setPixelSize(double pixelSize) {
        this.pixelSize = pixelSize;
    }

}
