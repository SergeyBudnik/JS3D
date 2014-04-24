package js3d.math.solvers;

import js3d.math.utils.DoubleUtils;

@SuppressWarnings("unused")
public class Eq2Solver {
    public double [] solve(double a, double b, double c, double eps) {
        if (DoubleUtils.isZero(a, eps)) {
            if (DoubleUtils.isZero(b, eps)) {
                if (DoubleUtils.isZero(c, eps)) {
                    return new double [] {0};
                } else {
                    return new double [] {};
                }
            } else {
                return new double [] {-c / b};
            }
        } else {
            double d = b * b - 4 * a * c;

            if (DoubleUtils.isNegative(d, eps)) {
                return new double [] {};
            } else if (DoubleUtils.isZero(d, eps)) {
                return new double [] {-b / (2 * a)};
            } else {
                double sqrtD = Math.sqrt(d);

                if (DoubleUtils.isPositive(a, eps)) {
                    return new double [] {
                            (-b - sqrtD) / (2 * a),
                            (-b + sqrtD) / (2 * a)
                    };
                } else {
                    return new double [] {
                            (-b + sqrtD) / (2 * a),
                            (-b - sqrtD) / (2 * a)
                    };
                }
            }
        }
    }
}
