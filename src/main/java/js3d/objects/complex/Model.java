package js3d.objects.complex;

import js3d.objects.core.ClosedObject;
import js3d.objects.intangible.Ray;
import js3d.objects.primitives.Triangle;
import js3d.objects.support.IntersectionInfo;
import js3d.objects.support.SingleIntersectionInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Model implements ClosedObject {
    private Collection<Triangle> triangles;

    public Model(Collection<Triangle> triangles) {
        this.triangles = triangles;
    }

    @Override
    public void rotateX(double x) {
        triangles.forEach(t -> t.rotateX(x));
    }

    @Override
    public void rotateY(double y) {
        triangles.forEach(t -> t.rotateY(y));
    }

    @Override
    public void rotateZ(double z) {
        triangles.forEach(t -> t.rotateZ(z));    }

    @Override
    public void translate(double x, double y, double z) {
        triangles.forEach(t -> t.translate(x, y, z));
    }

    @Override
    public void scale(double x, double y, double z) {
        triangles.forEach(t -> t.scale(x, y, z));
    }

    @Override
    public IntersectionInfo intersects(Ray r) {
        ArrayList<SingleIntersectionInfo> allIOs = new ArrayList<>();

        for (Triangle t : triangles) {
            IntersectionInfo io = t.intersects(r);

            if (io != null) {
                allIOs.add(io.getClosestIO());
            }
        }

        if (allIOs.size() == 0) {
            return null;
        }

        Collections.sort(allIOs, (SingleIntersectionInfo o1, SingleIntersectionInfo o2) -> {
            if (o1.dist < o2.dist) {
                return -1;
            } else if (o1.dist == o2.dist) {
                return 0;
            } else {
                return 1;
            }
        });

        return new IntersectionInfo(allIOs);
    }
}
