package js3d.math.core;

@SuppressWarnings("unused")
public class Vector {
    public double x, y, z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector add(Vector v) {
        return new Vector(x + v.x, y + v.y, z + v.z);
    }

    public Vector sub(Vector v) {
        return new Vector(x - v.x, y - v.y, z - v.z);
    }

    public Vector mul(double n) {
        return new Vector(n * x, n * y, n * z);
    }

    public Vector div(double n) {
        return new Vector(x / n, y / n, z / n);
    }

    public Vector mul(Matrix m) {
        return new Vector(
                x * m.m[0][0] + y * m.m[1][0] + z * m.m[2][0] + m.m[3][0],
                x * m.m[0][1] + y * m.m[1][1] + z * m.m[2][1] + m.m[3][1],
                x * m.m[0][2] + y * m.m[1][2] + z * m.m[2][2] + m.m[3][2]
        );
    }

    public double dot(Vector v) {
        return x * v.x + y * v.y + z * v.z;
    }

    public Vector cross(Vector v) {
        return new Vector(z * v.y - y * v.z, x * v.z - z * v.x, y * v.x - x * v.y);
    }

    public double len2() {
        return dot(this);
    }

    public double len() {
        double len2 = len2();

        if (len2 == 0 || len2 == 1) {
            return len2;
        }

        return Math.sqrt(len2);
    }

    public Vector normal() {
        double l = len();

        if (l == 0 || l == 1) {
            return new Vector(x, y, z);
        }

        return this.div(l);
    }

    public static double cos(Vector u, Vector v) {
        double lu = u.len();
        double lv = v.len();

        if (lu == 0 || lv == 0) {
            return 0;
        }

        return u.dot(v) / (lu * lv);
    }
}
