package js3d.math.core;

@SuppressWarnings("unused")
public class Matrix {
    public static final int DIM = 4;

    public double [][] m = new double [DIM][DIM];

    public static Matrix unionMatrix() {
        Matrix m = new Matrix();

        m.m[0][0] = 1;
        m.m[1][1] = 1;
        m.m[2][2] = 1;
        m.m[3][3] = 1;

        return m;
    }

    public static Matrix translation(double tx, double ty, double tz) {
        Matrix m = unionMatrix();

        m.m[3][0] = tx;
        m.m[3][1] = ty;
        m.m[3][2] = tz;

        return m;
    }

    public static Matrix scaling(double sx, double sy, double sz) {
        Matrix m = unionMatrix();

        m.m[0][0] = sx;
        m.m[1][1] = sy;
        m.m[2][2] = sz;

        return m;
    }

    public static Matrix rotationX(double a) {
        Matrix m = unionMatrix();

        double cos = Math.cos(a);
        double sin = Math.sin(a);

        m.m[1][1] = cos;
        m.m[1][2] = sin;
        m.m[2][1] = -sin;
        m.m[2][2] = cos;

        return m;
    }

    public static Matrix rotationY(double a) {
        Matrix m = unionMatrix();

        double cos = Math.cos(a);
        double sin = Math.sin(a);

        m.m[0][0] = cos;
        m.m[0][2] = -sin;
        m.m[2][0] = sin;
        m.m[2][2] = cos;

        return m;
    }

    public static Matrix rotationZ(double a) {
        Matrix m = unionMatrix();

        double cos = Math.cos(a);
        double sin = Math.sin(a);

        m.m[0][0] = cos;
        m.m[0][1] = sin;
        m.m[1][0] = -sin;
        m.m[1][1] = cos;

        return m;
    }

    public Matrix reverse() {
        double det = det();
        double k = 1 / det;

        Matrix res = unionMatrix();

        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                res.m[j][i] = minor(i, j) * k;

                if ((i + j) % 2 != 0) {
                    res.m[j][i] *= -1;
                }
            }
        }

        return res;
    }

    public double minor(int i, int j) {
        double [][] a = new double[DIM - 1][DIM - 1];

        for (int i1 = 0, i0 = 0; i1 < DIM - 1; i1++, i0++) {
            if (i0 == i) {
                i0++;
            }

            for (int j1 = 0, j0 = 0; j1 < DIM - 1; j1++, j0++) {
                if (j0 == j) {
                    j0++;
                }

                a[i1][j1] = m[i0][j0];
            }
        }

        return
            a[0][0] * a[1][1] * a[2][2] + a[0][1] * a[1][2] * a[2][0] + a[0][2] * a[1][0] * a[2][1] -
            a[0][2] * a[1][1] * a[2][0] - a[0][0] * a[1][2] * a[2][1] - a[0][1] * a[1][0] * a[2][2];
    }

    public double det() {
        return
            m[0][0] * m[1][1] * m[2][2] * m[3][3] - m[0][0] * m[1][1] * m[2][3] * m[3][2] -
            m[0][0] * m[1][2] * m[2][1] * m[3][3] + m[0][0] * m[1][2] * m[2][3] * m[3][1] +
            m[0][0] * m[1][3] * m[2][1] * m[3][2] - m[0][0] * m[1][3] * m[2][2] * m[3][1] -
            m[0][1] * m[1][0] * m[2][2] * m[3][3] + m[0][1] * m[1][0] * m[2][3] * m[3][2] +
            m[0][1] * m[1][2] * m[2][0] * m[3][3] - m[0][1] * m[1][2] * m[2][3] * m[3][0] -
            m[0][1] * m[1][3] * m[2][0] * m[3][2] + m[0][1] * m[1][3] * m[2][2] * m[3][0] +
            m[0][2] * m[1][0] * m[2][1] * m[3][3] - m[0][2] * m[1][0] * m[2][3] * m[3][1] -
            m[0][2] * m[1][1] * m[2][0] * m[3][3] + m[0][2] * m[1][1] * m[2][3] * m[3][0] +
            m[0][2] * m[1][3] * m[2][0] * m[3][1] - m[0][2] * m[1][3] * m[2][1] * m[3][0] -
            m[0][3] * m[1][0] * m[2][1] * m[3][2] + m[0][3] * m[1][0] * m[2][2] * m[3][1] +
            m[0][3] * m[1][1] * m[2][0] * m[3][2] - m[0][3] * m[1][1] * m[2][2] * m[3][0] -
            m[0][3] * m[1][2] * m[2][0] * m[3][1] + m[0][3] * m[1][2] * m[2][1] * m[3][0];
    }

    public Matrix mul(Matrix m) {
        Matrix res = new Matrix();

        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                double c = 0;

                for (int k = 0; k < DIM; k++) {
                    c += this.m[i][k] * m.m[k][j];
                }

                res.m[i][j] = c;
            }
        }

        return res;
    }
}
