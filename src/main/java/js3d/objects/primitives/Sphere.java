package js3d.objects.primitives;

import js3d.math.core.Vector;
import js3d.math.solvers.Eq2Solver;
import js3d.math.utils.DoubleUtils;
import js3d.objects.core.ClosedObject;
import js3d.objects.intangible.Ray;
import js3d.objects.support.IntersectionInfo;
import js3d.objects.support.Material;
import js3d.objects.support.SingleIntersectionInfo;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class Sphere extends Primitive implements ClosedObject {
    private Eq2Solver eq2Solver = new Eq2Solver();

    private Material mat;
    private double rad;

    public Sphere(Material mat, double rad) {
        super(mat);

        this.mat = mat;
        this.rad = rad;
    }

    @Override
    protected IntersectionInfo intersectsInner(Ray r) {
        Vector v = r.pos;

        double sx2 = sx * sx;
        double sy2 = sy * sy;
        double sz2 = sz * sz;

        double a = r.dir.x * r.dir.x / sx2 + r.dir.y * r.dir.y / sy2 + r.dir.z * r.dir.z / sz2;
        double b = 2 * (r.dir.x * r.pos.x / sx2 + r.dir.y * r.pos.y / sy2 + r.dir.z * r.pos.z / sz2);
        double c = r.pos.x * r.pos.x / sx2 + r.pos.y * r.pos.y / sy2 + r.pos.z * r.pos.z / sz2 - rad * rad;

        double [] res = eq2Solver.solve(a, b, c, 1e-10);

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
            Vector pos = r.pos.add(r.dir.mul(res[i]));

            allIOs.add(new SingleIntersectionInfo(pos, normal(pos), res[i], material));
        }

        return new IntersectionInfo(allIOs);
    }

    private Vector normal(Vector p) {
        return p.normal();
    }
}
