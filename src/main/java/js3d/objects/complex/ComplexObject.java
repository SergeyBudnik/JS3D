package js3d.objects.complex;

import js3d.objects.core.ClosedObject;
import js3d.objects.core.SceneObject;

public abstract class ComplexObject implements ClosedObject {
    protected ClosedObject o1, o2;

    protected ComplexObject(ClosedObject o1, ClosedObject o2) {
        this.o1 = o1;
        this.o2 = o2;
    }

    @Override
    public void rotateX(double x) {
        o1.rotateX(x);
        o2.rotateX(x);
    }

    @Override
    public void rotateY(double y) {
        o1.rotateY(y);
        o2.rotateY(y);
    }

    @Override
    public void rotateZ(double z) {
        o1.rotateZ(z);
        o2.rotateZ(z);
    }

    @Override
    public void translate(double x, double y, double z) {
        o1.translate(x, y, z);
        o2.translate(x, y, z);
    }

    @Override
    public void scale(double x, double y, double z) {
        o1.scale(x, y, z);
        o2.scale(x, y, z);
    }
}
