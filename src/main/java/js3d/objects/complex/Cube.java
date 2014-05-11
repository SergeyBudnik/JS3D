package js3d.objects.complex;

import js3d.math.core.Vector;
import js3d.objects.core.ClosedObject;
import js3d.objects.core.SceneObject;
import js3d.objects.intangible.Ray;
import js3d.objects.primitives.Triangle;
import js3d.objects.support.IntersectionInfo;
import js3d.objects.support.Material;
import js3d.objects.support.SingleIntersectionInfo;

import java.util.ArrayList;
import java.util.Collections;

@SuppressWarnings("unused")
public class Cube implements ClosedObject {
    private SceneObject t11, t12, t21, t22, t31, t32, t41, t42, t51, t52, t61, t62;

    private SceneObject [] allTs;

    public Cube(double w, Material m) {
        t11 = getT1(w, m);
        t12 = getT2(w, m);

        t11.translate(-w / 2, -w / 2, -w / 2);
        t12.translate(-w / 2, -w / 2, -w / 2);

        t21 = getT1(w, m);
        t22 = getT2(w, m);

        t21.translate(-w / 2, w / 2, -w / 2);
        t22.translate(-w / 2, w / 2, -w / 2);

        t31 = getT1(w, m);
        t32 = getT2(w, m);

        t31.rotateX(Math.PI / 2);
        t31.translate(-w / 2, w / 2, -w / 2);

        t32.rotateX(Math.PI / 2);
        t32.translate(-w / 2, w / 2, -w / 2);

        t41 = getT1(w, m);
        t42 = getT2(w, m);

        t41.rotateX(Math.PI / 2);
        t41.translate(-w / 2, w / 2, w / 2);

        t42.rotateX(Math.PI / 2);
        t42.translate(-w / 2, w / 2, w / 2);

        t51 = getT1(w, m);
        t52 = getT2(w, m);

        t51.rotateZ(Math.PI / 2);
        t51.translate(-w / 2, -w / 2, -w / 2);

        t52.rotateZ(Math.PI / 2);
        t52.translate(-w / 2, -w / 2, -w / 2);

        t61 = getT1(w, m);
        t62 = getT2(w, m);

        t61.rotateZ(Math.PI / 2);
        t61.translate(w / 2, -w / 2, -w / 2);

        t62.rotateZ(Math.PI / 2);
        t62.translate(w / 2, -w / 2, -w / 2);

        allTs = new SceneObject [] {t11, t12, t21, t22, t31, t32, t41, t42, t51, t52, t61, t62};
    }

    @Override
    public void rotateX(double x) {
        for (SceneObject t : allTs) {
            t11.rotateX(x);
        }
    }

    @Override
    public void rotateY(double y) {
        for (SceneObject t : allTs) {
            t11.rotateY(y);
        }
    }

    @Override
    public void rotateZ(double z) {
        for (SceneObject t : allTs) {
            t11.rotateZ(z);
        }

    }

    @Override
    public void translate(double x, double y, double z) {
        for (SceneObject t : allTs) {
            t.translate(x, y, z);
        }

    }

    @Override
    public void scale(double x, double y, double z) {
        for (SceneObject t : allTs) {
            t11.scale(x, y, z);
        }

    }

    @Override
    public IntersectionInfo intersects(Ray r) {
        IntersectionInfo [] ios = {
                t11.intersects(r), t21.intersects(r), t31.intersects(r),
                t41.intersects(r), t51.intersects(r), t61.intersects(r),
                t12.intersects(r), t22.intersects(r), t32.intersects(r),
                t42.intersects(r), t52.intersects(r), t62.intersects(r)
        };

        ArrayList<SingleIntersectionInfo> allIOs = new ArrayList<>();

        for (IntersectionInfo io : ios) {
            if (io != null) {
                allIOs.addAll(io.allIOs);
            }
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

    private SceneObject getT1(double w, Material m) {
        return new Triangle(new Vector(0, 0, 0), new Vector(w, 0, 0), new Vector(0, 0, w), m);
    }

    private SceneObject getT2(double w, Material m) {
        return new Triangle(new Vector(w, 0, w), new Vector(w, 0, 0), new Vector(0, 0, w), m);
    }
}
