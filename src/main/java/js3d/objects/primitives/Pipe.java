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
public class Pipe extends Primitive {
    private Eq2Solver eq2Solver = new Eq2Solver();

    public double h, rad;

    public Pipe(double h, double rad, Material m) {
        super(m);

        this.h = h;
        this.rad = rad;
    }

    @Override
    protected IntersectionInfo intersectsInner(Ray r) {
        double sx2 = sx * sx;
        double sz2 = sz * sz;

        double a = r.dir.x * r.dir.x / sx2 + r.dir.z * r.dir.z / sz2;
        double b = 2 * (r.pos.x * r.dir.x / sx2 + r.pos.z * r.dir.z / sz2);
        double c = r.pos.x * r.pos.x / sx2 + r.pos.z * r.pos.z / sz2 - rad * rad;

        double [] allRes = eq2Solver.solve(a, b, c, 1e-10);

        int i;

        for (i = 0; i < allRes.length; i++) {
            double y = r.pos.y + r.dir.y * allRes[i];

            if (DoubleUtils.isPositive(allRes[i], 1e-10) && -sy * h / 2 <= y && y <= sy * h / 2) {
                break;
            }
        }

        if (i == allRes.length) {
            return null;
        }

        ArrayList<SingleIntersectionInfo> allIOs = new ArrayList<>();

        for (; i < allRes.length; i++) {
            Vector pos = r.pos.add(r.dir.mul(allRes[i]));

            allIOs.add(new SingleIntersectionInfo(pos, normal(pos), allRes[i], material));
        }

        return new IntersectionInfo(allIOs);
    }

    private Vector normal(Vector p) {
        return new Vector(sx * p.x, 0, sz * p.z).normal();
    }
}
