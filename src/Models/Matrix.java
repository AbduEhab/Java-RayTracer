package Models;

import Models.Tuples.Point;
import Models.Tuples.Vector;

public class Matrix {
    private double[][] matrix;
    private int size;

    private static final double[][] IdentityArray = { { 1, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 1 } };

    public static final Matrix IDENTITY = new Matrix(4, IdentityArray);

    public Matrix(int size) {
        matrix = new double[size][size];
        this.size = size;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    public Matrix(int size, double[][] InitialFields) {
        matrix = new double[size][size];
        this.size = size;
        setMatrix(InitialFields);
    }

    public boolean equals(Matrix b) {
        if (size != b.size)
            return false;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j] - b.getElement(i, j) > 0.00001)
                    return false;
            }
        }

        return true;
    }

    public Matrix multiply(Matrix b) {
        Matrix temp = new Matrix(size);

        double result = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result += matrix[i][0] * b.getElement(0, j) + matrix[i][1] * b.getElement(1, j)
                        + matrix[i][2] * b.getElement(2, j) + matrix[i][3] * b.getElement(3, j);
                temp.setElement(i, j, result);
                result = 0;
            }
        }
        return temp;
    }

    public Point multiply(Point b) {
        double[] res = new double[4];

        double[] mb = { b.getX(), b.getY(), b.getZ(), 1 };

        for (int i = 0; i < size - 1; i++) {
            double temp = 0;
            for (int j = 0; j < size; j++) {
                temp += matrix[i][j] * mb[j];
            }
            res[i] = temp;
        }
        return new Point(res[0], res[1], res[2]);
    }

    public Vector multiply(Vector b) {
        double[] res = new double[4];

        double[] mb = { b.getX(), b.getY(), b.getZ(), 0 };

        for (int i = 0; i < size - 1; i++) {
            double temp = 0;
            for (int j = 0; j < size; j++) {
                temp += matrix[i][j] * mb[j];
            }
            res[i] = temp;
        }

        return new Vector(res[0], res[1], res[2]);
    }

    public Matrix multiplyByIdentity() {
        double result = 0;

        Matrix temp = new Matrix(size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result += matrix[i][0] * IdentityArray[0][j] + matrix[i][1] * IdentityArray[1][j]
                        + matrix[i][2] * IdentityArray[2][j] + matrix[i][3] * IdentityArray[3][j];
                temp.setElement(i, j, result);
                result = 0;
            }
        }
        return temp;
    }

    public Matrix transpose() {
        Matrix temp = new Matrix(size);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                temp.setElement(i, j, matrix[j][i]);
            }
        }
        return temp;
    }

    public double determinant() {
        return determinantHelper(this);
    }

    private double determinantHelper(Matrix matrix) {
        if (matrix.size == 2) {
            return matrix.getElement(0, 0) * matrix.getElement(1, 1)
                    - matrix.getElement(0, 1) * matrix.getElement(1, 0);
        } else {
            double res = 0;

            for (int i = 0; i < matrix.size; i++) {
                if (i % 2 == 0)
                    res += matrix.getElement(i, 0) * determinantHelper(matrix.subMatrix(i, 0));
                else
                    res -= matrix.getElement(i, 0) * determinantHelper(matrix.subMatrix(i, 0));

            }
            return res;
        }

    }

    private Matrix subMatrix(int a, int b) {
        Matrix temp = new Matrix(this.size - 1);
        boolean aReplaced = false;
        boolean bReplaced = false;
        for (int i = 0; i < size; i++) {
            bReplaced = false;
            if (i != a)
                for (int j = 0; j < size; j++) {
                    if (j != b)
                        temp.setElement(aReplaced ? i - 1 : i, bReplaced ? j - 1 : j, matrix[i][j]);
                    else
                        bReplaced = true;
                }
            else
                aReplaced = true;
        }
        return temp;
    }

    // I was lit loosing my mind so i did't worry about readability as much as the
    // other methods
    public Matrix cofactor() {

        Matrix temp = new Matrix(size);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                temp.setElement(i, j, Math.pow(-1, i + j) * (subMatrix(i, j).determinant()));
            }
        }
        return temp;
    }

    public boolean invertable() {
        return determinant() == 0 ? false : true;
    }

    public Matrix inverse() {
        double det = determinant();
        Matrix cofactor = cofactor(); // to self: transpose this if you change the j & i again

        Matrix temp = new Matrix(size);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                temp.setElement(i, j, cofactor.getElement(j, i) / det); // j & i exchanged for the time being
            }
        }

        return temp;
    }

    public Matrix translate(double x, double y, double z) {

        Matrix translationMatrix = new Matrix(4);
        double[][] tv = { { 1, 0, 0, x }, { 0, 1, 0, y }, { 0, 0, 1, z }, { 0, 0, 0, 1 } };
        translationMatrix.setMatrix(tv);

        return this.multiply(translationMatrix);
    }

    public Matrix scale(double x, double y, double z) {

        Matrix scalingMatrix = new Matrix(4);
        double[][] tv = { { x, 0, 0, 0 }, { 0, y, 0, 0 }, { 0, 0, z, 0 }, { 0, 0, 0, 1 } };
        scalingMatrix.setMatrix(tv);

        return this.multiply(scalingMatrix);
    }

    public Matrix rotateX(double radians) {

        Matrix rotationMatrix = new Matrix(4);
        double[][] rv = { { 1, 0, 0, 0 }, { 0, Math.cos(radians), -Math.sin(radians), 0 },
                { 0, Math.sin(radians), Math.cos(radians), 0 }, { 0, 0, 0, 1 } };
        rotationMatrix.setMatrix(rv);

        return this.multiply(rotationMatrix);
    }

    public Matrix rotateY(double radians) {

        Matrix rotationMatrix = new Matrix(4);
        double[][] rv = { { Math.cos(radians), 0, Math.sin(radians), 0 }, { 0, 1, 0, 0 },
                { -Math.sin(radians), 0, Math.cos(radians), 0 }, { 0, 0, 0, 1 } };
        rotationMatrix.setMatrix(rv);

        return this.multiply(rotationMatrix);
    }

    public Matrix rotateZ(double radians) {

        Matrix rotationMatrix = new Matrix(4);
        double[][] rv = { { Math.cos(radians), -Math.sin(radians), 0, 0 },
                { Math.sin(radians), Math.cos(radians), 0, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 1 } };
        rotationMatrix.setMatrix(rv);

        return this.multiply(rotationMatrix);
    }

    public Matrix shear(double Xy, double Xz, double Yx, double Yz, double Zx, double Zy) {

        Matrix shearingMatrix = new Matrix(4);
        double[][] sv = { { 1, Xy, Xz, 0 }, { Yx, 1, Yz, 0 }, { Zx, Zy, 1, 0 }, { 0, 0, 0, 1 } };
        shearingMatrix.setMatrix(sv);

        return this.multiply(shearingMatrix);
    }

    public double getElement(int i, int j) {
        return matrix[i][j];
    }

    public Matrix setElement(int i, int j, double newValue) {
        matrix[i][j] = newValue;
        return this;
    }

    public Matrix setMatrix(double[][] b) {

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = b[i][j];
            }
        }
        return this;
    }

    public int getSize() {
        return size;
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < size; i++) {
            s += "|\t";
            for (int j = 0; j < size; j++) {
                s += matrix[i][j];
                if (j != 3)
                    s += "\t|\t";
            }
            ;
            s += "\t|\n";
        }

        return s;
    }
}