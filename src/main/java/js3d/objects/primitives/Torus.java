package js3d.objects.primitives;

import js3d.math.core.Vector;
import js3d.math.solvers.Eq4Solver;
import js3d.math.utils.DoubleUtils;
import js3d.objects.core.ClosedObject;
import js3d.objects.intangible.Ray;
import js3d.objects.support.IntersectionInfo;
import js3d.objects.support.Material;
import js3d.objects.support.SingleIntersectionInfo;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class Torus extends Primitive implements ClosedObject {
    private Eq4Solver eq4Solver = new Eq4Solver();

    private double r, R;

    public Torus(double r, double R, Material m) {
        super(m);

        this.r = r;
        this.R = R;
    }

    @Override
    protected IntersectionInfo intersectsInner(Ray ray) {
        double x0 = ray.pos.x / sx;
        double y0 = ray.pos.y / sy;
        double z0 = ray.pos.z / sz;

        double dx = ray.dir.x / sx;
        double dy = ray.dir.y / sy;
        double dz = ray.dir.z / sz;

        double A = x0 * x0 + y0 * y0 + z0 * z0 + R * R - r * r;
        double B = 2 * (x0 * dx + y0 * dy + z0 * dz);
        double C = (dx * dx + dy * dy + dz * dz);
        double D = 4 * R * R * (x0 * x0 + y0 * y0);
        double E = 8 * R * R * (x0 * dx + y0 * dy);
        double F = 4 * R * R * (dx * dx + dy * dy);

        double a = C * C;
        double b = 2 * C * B;
        double c = 2 * A * C + B * B - F;
        double d = 2 * A * B - E;
        double e = A * A - D;

        double [] res = eq4Solver.solve(a, b, c, d, e, 1e-10);

        if (res.length == 0) {
            return null;
        } else {
            int i;

            for (i = 0; i < res.length; i++) {
                if (DoubleUtils.isPositive(res[i], 1e-10)) {
                    break;
                }
            }

            if (i == res.length) {
                return null;
            }

            ArrayList<SingleIntersectionInfo> allIOs = new ArrayList<>();

            for (; i < res.length; i++) {
                Vector pos = ray.pos.add(ray.dir.mul(res[i]));

                allIOs.add(new SingleIntersectionInfo(pos, normal(pos), res[i], material));
            }

            return new IntersectionInfo(allIOs);
        }
    }

    private Vector normal(Vector p) {
        double x0 = p.x / sx;
        double y0 = p.y / sy;
        double z0 = p.z / sz;

        double a = x0 * x0 + y0 * y0 + z0 * z0 - r * r;
        double b1 = a - R * R;
        double b2 = a + R * R;

        double dfDx = 4 * x0 * b1;
        double dfDy = 4 * y0 * b1;
        double dfDz = 4 * z0 * b2;

        return new Vector(dfDx, dfDy, dfDz).normal();
    }
}
