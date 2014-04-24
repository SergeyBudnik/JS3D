package js3d.objects.complex;

import js3d.objects.core.ClosedObject;
import js3d.objects.core.SceneObject;
import js3d.objects.intangible.Ray;
import js3d.objects.primitives.Circle;
import js3d.objects.primitives.Pipe;
import js3d.objects.support.IntersectionInfo;
import js3d.objects.support.Material;
import js3d.objects.support.SingleIntersectionInfo;

import java.util.ArrayList;
import java.util.Collections;

@SuppressWarnings("unused")
public class Cylinder implements ClosedObject {
    Pipe pipe;
    SceneObject top, bottom;

    public Cylinder(double h, double rad, Material m) {
        pipe = getPipe(h, rad, m);
        top = getTop(h, rad, m);
        bottom = getBottom(h, rad, m);
    }

    @Override
    public void rotateX(double x) {
        pipe.rotateX(x);
        top.rotateX(x);
        bottom.rotateX(x);
    }

    @Override
    public void rotateY(double y) {
        pipe.rotateY(y);
        top.rotateY(y);
        bottom.rotateY(y);
    }

    @Override
    public void rotateZ(double z) {
        pipe.rotateZ(z);
        top.rotateZ(z);
        bottom.rotateZ(z);
    }

    @Override
    public void translate(double x, double y, double z) {
        pipe.translate(x, y, z);
        top.translate(x, y, z);
        bottom.translate(x, y, z);
    }

    @Override
    public void scale(double x, double y, double z) {
        top.scale(x, y, z);

        top.translate(0, -pipe.sy * pipe.h / 2, 0);

        bottom.scale(x, y, z);

        bottom.translate(0, pipe.sy * pipe.h / 2, 0);

        pipe.scale(x, y, z);

        top.translate(0, pipe.sy * pipe.h / 2, 0);
        bottom.translate(0, -pipe.sy * pipe.h / 2, 0);
    }

    @Override
    public IntersectionInfo intersects(Ray r) {
        IntersectionInfo io1 = pipe.intersects(r);
        IntersectionInfo io2 = top.intersects(r);
        IntersectionInfo io3 = bottom.intersects(r);

        ArrayList<SingleIntersectionInfo> allIOs = new ArrayList<>();

        if (io1 != null) {
            allIOs.addAll(io1.allIOs);
        }

        if (io2 != null) {
            allIOs.addAll(io2.allIOs);
        }

        if (io3 != null) {
            allIOs.addAll(io3.allIOs);
        }

        if (allIOs.size() == 0) {
            return null;
        }

        Collections.sort(allIOs, (SingleIntersectionInfo sio1, SingleIntersectionInfo sio2) -> {
            if (sio1.dist < sio2.dist) {
                return -1;
            } else if (sio1.dist == sio2.dist) {
                return 0;
            } else {
                return 1;
            }
        });

        return new IntersectionInfo(allIOs);
    }

    private Pipe getPipe(double h, double rad, Material m) {
        return new Pipe(h, rad, m);
    }

    private SceneObject getTop(double h, double rad, Material m) {
        SceneObject top = new Circle(rad, m);

        top.translate(0, h / 2, 0);

        return top;
    }

    private SceneObject getBottom(double h, double rad, Material m) {
        SceneObject bottom = new Circle(rad, m);

        bottom.translate(0, -h / 2, 0);

        return bottom;
    }
}
