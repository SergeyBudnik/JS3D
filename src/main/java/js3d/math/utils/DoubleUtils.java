package js3d.math.utils;

@SuppressWarnings("unused")
public class DoubleUtils {
    public static boolean isPositive(double n, double eps) {
        return n >= eps;
    }

    public static boolean isNegative(double n, double eps) {
        return n <= -eps;
    }

    public static boolean isZero(double n, double eps) {
        return -eps < n && n < eps;
    }

    public static boolean equals(double n, double m, double eps) {
        return Math.abs(n - m) < eps;
    }
}
