package Models;

public class Camera {
    int hSize;
    int vSize;
    double fov;
    Matrix transform = Matrix.IDENTITY;

    public Camera(int hSize, int vSize, double fov) {
        this.hSize = hSize;
        this.vSize = vSize;
        this.fov = fov;
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

    public int gethSize() {
        return hSize;
    }

    public void sethSize(int hSize) {
        this.hSize = hSize;
    }

    public int getvSize() {
        return vSize;
    }

    public void setvSize(int vSize) {
        this.vSize = vSize;
    }

    public double getFov() {
        return fov;
    }

    public void setFov(double fov) {
        this.fov = fov;
    }

    public Matrix getTransform() {
        return transform;
    }

    public void setTransform(Matrix transform) {
        this.transform = transform;
    }

    
}
