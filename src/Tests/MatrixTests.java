package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import Models.Matrix;
import Models.Point;

public class MatrixTests {

    @Test
    @DisplayName("Create Matrix")
    public void createMatrix() {

        Matrix m = new Matrix(4);

        assertEquals(0, m.getElement(3, 3), "Matrix not initiated correctly");
    }

    @Test
    @DisplayName("Equal Matrix")
    public void equalMatrix() {

        Matrix m = new Matrix(4);
        m.setElement(3, 3, 1);
        Matrix b = new Matrix(4);

        assertEquals(false, m.equals(b), "Matrix equals method not implemented correctly");

        b.setElement(3, 3, 1);
        assertEquals(true, m.equals(b), "Matrix equals method not implemented correctly");

    }

    @Test
    @DisplayName("Multiply Matrix")
    public void multiplyMatrix() {

        Matrix m = new Matrix(4);
        double[][] nm = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 8, 7, 6 }, { 5, 4, 3, 2 } };
        m.setMatrix(nm);

        Matrix b = new Matrix(4);
        double[][] nb = { { -2, 1, 2, 3 }, { 3, 2, 1, -1 }, { 4, 3, 6, 5 }, { 1, 2, 7, 8 } };
        b.setMatrix(nb);

        Matrix res = new Matrix(4);
        double[][] nr = { { 20, 22, 50, 48 }, { 44, 54, 114, 108 }, { 40, 58, 110, 102 }, { 16, 26, 46, 42 } };
        res.setMatrix(nr);

        assertEquals(true, m.multiply(b).equals(res), "Matrix-Matrix multiply method not implemented correctly");
    }

    @Test
    @DisplayName("Multiply Matrix By Tuple")
    public void multiplyMatrixByTuple() {

        Matrix m = new Matrix(4);
        double[][] nm = { { 1, 2, 3, 4 }, { 2, 4, 4, 2 }, { 8, 6, 4, 1 }, { 0, 0, 0, 1 } };
        m.setMatrix(nm);

        Point p = new Point(1, 2, 3);

        Point res = new Point(18, 24, 33);

        assertEquals(true, m.multiply(p).equals(res), "Matrix-Tuple multiply method not implemented correctly");
    }

    @Test
    @DisplayName("Multiply Matrix By Identity-Matrix")
    public void multiplyMatrixByIdentity() {

        Matrix m = new Matrix(4);
        double[][] nm = { { 1, 2, 3, 4 }, { 2, 4, 4, 2 }, { 8, 6, 4, 1 }, { 0, 0, 0, 1 } };
        m.setMatrix(nm);

        assertEquals(true, m.multiplyByIdentity().equals(m),
                "Matrix-Identity multiply method not implemented correctly");
    }

    @Test
    @DisplayName("Transpose Matrix")
    public void transposeMatrix() {

        Matrix m = new Matrix(4);
        double[][] nm = { { 0, 9, 3, 0 }, { 9, 8, 0, 8 }, { 1, 8, 5, 3 }, { 0, 0, 5, 8 } };
        m.setMatrix(nm);

        Matrix res = new Matrix(4);
        double[][] nres = { { 0, 9, 1, 0 }, { 9, 8, 8, 0 }, { 3, 0, 5, 5 }, { 0, 8, 3, 8 } };
        res.setMatrix(nres);

        assertEquals(true, m.transpose().equals(res), "Matrix Transpose method not implemented correctly");
    }

    @Test
    @DisplayName("Transpose Identity-Matrix")
    public void transposeIdentityMatrix() {

        double[][] nm = { { 1, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 1 } };

        Matrix identity = new Matrix(4);
        identity.setMatrix(nm);

        assertEquals(true, identity.transpose().equals(identity),
                "Identity-Matrix Transpose method not implemented correctly");
    }

    @Test
    @DisplayName("Matrix Cofactor")
    public void matrixCofactor() {

        double[][] m1array = { { 3, 5, 0 }, { 2, -1, -7 }, { 6, -1, 5 } };

        Matrix m1 = new Matrix(3);
        m1.setMatrix(m1array);

        assertEquals(-25, m1.cofactor().getElement(1, 0), "Matrix Cofactor method not implemented correctly");
    }

    @Test
    @DisplayName("Matrix Determinant")
    public void matrixDeterminant() {

        double[][] m1array = { { 1, 2, 6 }, { -5, 8, -4 }, { 2, 6, 4 } };

        Matrix m1 = new Matrix(3);
        m1.setMatrix(m1array);

        double[][] m2array = { { -2, -8, 3, 5 }, { -3, 1, 7, 3 }, { 1, 2, -9, 6 }, { -6, 7, 7, -9 } };

        Matrix m2 = new Matrix(4);
        m2.setMatrix(m2array);

        assertEquals(-196, m1.determinant(), "Matrix Determinant method not implemented correctly");
        assertEquals(-4071, m2.determinant(), "Matrix Determinant method not implemented correctly");
    }

    @Test
    @DisplayName("Matrix Invertable")
    public void matrixInvertable() {

        double[][] m1array = { { 1, 2, 6 }, { -5, 8, -4 }, { 2, 6, 4 } };

        Matrix m1 = new Matrix(3);
        m1.setMatrix(m1array);

        double[][] m2array = { { -4, 2, -2, -3 }, { 9, 6, 2, 6 }, { 0, -5, 1, -5 }, { 0, 0, 0, 0 } };

        Matrix m2 = new Matrix(4);
        m2.setMatrix(m2array);

        assertEquals(true, m1.invertable(), "Matrix Invertable method not implemented correctly");
        assertEquals(false, m2.invertable(), "Matrix Invertable method not implemented correctly");
    }

    @Test
    @DisplayName("Matrix Invertion")
    public void matrixInvertion() {

        double[][] m1array = { { -5, 2, 6, -8 }, { 1, -5, 1, 8 }, { 7, 7, -6, -7 }, { 1, -3, 7, 4 } };

        Matrix m1 = new Matrix(4);
        m1.setMatrix(m1array);

        double[][] resarray = { { 0.21805, 0.45113, 0.24060, -0.04511 }, { -0.80827, -1.45677, -0.44361, 0.52068 },
                { -0.07895, -0.22368, -0.05263, 0.19737 }, { -0.52256, -0.81391, -0.30075, 0.30639 } };

        Matrix res = new Matrix(4);
        res.setMatrix(resarray);

        assertEquals(true, m1.inverse().equals(res), "Matrix Invertion method not implemented correctly");
    }
}
