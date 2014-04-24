package js3d.objects.primitives;

import js3d.math.core.Matrix;
import js3d.math.core.Vector;
import js3d.objects.intangible.Ray;
import js3d.objects.support.IntersectionInfo;
import js3d.objects.support.Material;
import js3d.objects.support.SingleIntersectionInfo;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class Triangle extends Primitive {
    private Vector a, b, c;

    private Vector e0, e1, e2;
    private double e1e1, e1e2, e2e2;

    private Vector n = null;

    public Triangle(Vector a, Vector b, Vector c, Material m) {
        super(m);

        this.a = a;
        this.b = b;
        this.c = c;

        recalculateData();
    }

    @Override
    public void scale(double x, double y, double z) {
        super.scale(x, y, z);

        recalculateData();
    }

    private void recalculateData() {
        Matrix sm = Matrix.scaling(sx, sy, sz);

        e0 = a.mul(sm);
        e1 = b.mul(sm).sub(a.mul(sm));
        e2 = c.mul(sm).sub(a.mul(sm));

        n = e1.cross(e2).normal();

        e1e1 = e1.dot(e1);
        e1e2 = e1.dot(e2);
        e2e2 = e2.dot(e2);
    }

    @Override
    protected IntersectionInfo intersectsInner(Ray r) {
        Vector w0 = r.pos.sub(e0);
        double a = -n.dot(w0);
        double b = n.dot(r.dir);

        if (Math.abs(b) < 1e-5) {
            return null;
        }

        double c = a / b;

        if (c < 1e-5) {
            return null;
        }

        Vector pos = r.pos.add(r.dir.mul(c));

        Vector w = pos.sub(e0);

        double we1 = w.dot(e1);
        double we2 = w.dot(e2);

        double D = e1e2 * e1e2 - e1e1 * e2e2;

        double s = (e1e2 * we2 - e2e2 * we1) / D;

        if (s < 0.0 || s > 1.0) {
            return null;
        }

        double t = (e1e2 * we1 - e1e1 * we2) / D;

        if (t < 0.0 || (s + t) > 1.0) {
            return null;
        }

        ArrayList<SingleIntersectionInfo> allIOs = new ArrayList<>();

        allIOs.add(new SingleIntersectionInfo(pos, n, c, material));

        return new IntersectionInfo(allIOs);
    }
}
