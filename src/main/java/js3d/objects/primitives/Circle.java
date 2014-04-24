package js3d.objects.primitives;

import js3d.math.core.Vector;
import js3d.math.utils.DoubleUtils;
import js3d.objects.intangible.Ray;
import js3d.objects.support.IntersectionInfo;
import js3d.objects.support.Material;
import js3d.objects.support.SingleIntersectionInfo;

import java.util.ArrayList;

public class Circle extends Primitive {
    private double rad;

    public Circle(double rad, Material material) {
        super(material);

        this.rad = rad;
    }

    @Override
    protected IntersectionInfo intersectsInner(Ray r) {
        double sx2 = sx * sx;
        double sz2 = sz * sz;

        double a = r.dir.x * r.dir.x / sx2 + r.dir.z * r.dir.z / sz2;
        double b = 2 * (r.pos.x * r.dir.x / sx2 + r.pos.z * r.dir.z / sz2);
        double c = r.pos.x * r.pos.x / sx2 + r.pos.z * r.pos.z / sz2 - rad * rad;

        double t = -r.pos.y / r.dir.y;

        if (DoubleUtils.isPositive(t, 1e-10) && !DoubleUtils.isPositive(a * t * t + b * t + c, 1e-10)) {
            Vector pos = r.pos.add(r.dir.mul(t));

            final Vector n;

            if (r.dir.y > 0) {
                n = new Vector(0, -1, 0);
            } else {
                n = new Vector(0,  1, 0);
            }

            ArrayList<SingleIntersectionInfo> allIOs = new ArrayList<>();

            allIOs.add(new SingleIntersectionInfo(pos, n, t, material));

            return new IntersectionInfo(allIOs);
        }

        return null;
    }
}
