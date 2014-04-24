package js3d.objects.primitives;

import js3d.math.core.Matrix;
import js3d.math.core.Vector;
import js3d.objects.core.SceneObject;
import js3d.objects.intangible.Ray;
import js3d.objects.support.IntersectionInfo;
import js3d.objects.support.Material;
import js3d.objects.support.SingleIntersectionInfo;

@SuppressWarnings("unused")
public abstract class Primitive implements SceneObject {
    private Matrix allTransform = Matrix.unionMatrix();
    private Matrix rotateTransform = Matrix.unionMatrix();

    private Matrix allTransformRev = Matrix.unionMatrix();
    private Matrix rotateTransformRev = Matrix.unionMatrix();

    public double sx = 1, sy = 1, sz = 1;

    protected Material material;

    protected Primitive(Material material) {
        this.material = material;
    }

    @Override
    public void rotateX(double x) {
        allTransform = allTransform.mul(Matrix.rotationX(x));
        rotateTransform = rotateTransform.mul(Matrix.rotationX(x));

        allTransformRev = allTransform.reverse();
        rotateTransformRev = rotateTransform.reverse();
    }

    @Override
    public void rotateY(double y) {
        allTransform = allTransform.mul(Matrix.rotationY(y));
        rotateTransform = rotateTransform.mul(Matrix.rotationY(y));

        allTransformRev = allTransform.reverse();
        rotateTransformRev = rotateTransform.reverse();
    }

    @Override
    public void rotateZ(double z) {
        allTransform = allTransform.mul(Matrix.rotationZ(z));
        rotateTransform = rotateTransform.mul(Matrix.rotationZ(z));

        allTransformRev = allTransform.reverse();
        rotateTransformRev = rotateTransform.reverse();
    }

    @Override
    public void translate(double x, double y, double z) {
        allTransform = allTransform.mul(Matrix.translation(x, y, z));

        allTransformRev = allTransform.reverse();
    }

    @Override
    public void scale(double x, double y, double z) {
        sx *= x;
        sy *= y;
        sz *= z;
    }

    @Override
    public final IntersectionInfo intersects(Ray r) {
        Vector r1 = r.pos.mul(allTransformRev);
        Vector r2 = r.dir.mul(rotateTransformRev);

        IntersectionInfo io = intersectsInner(new Ray(r1, r2));

        if (io != null) {
            for (int i = 0; i < io.allIOs.size(); i++) {
                SingleIntersectionInfo sio = io.allIOs.get(i);

                sio.pos = sio.pos.mul(allTransform);
                sio.normal = sio.normal.mul(rotateTransform);
            }
        }

        return io;
    }

    protected abstract IntersectionInfo intersectsInner(Ray r);
}
