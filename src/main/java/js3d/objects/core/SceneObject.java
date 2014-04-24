package js3d.objects.core;

import js3d.objects.intangible.Ray;
import js3d.objects.support.IntersectionInfo;

@SuppressWarnings("unused")
public interface SceneObject {
    public void rotateX(double x);
    public void rotateY(double y);
    public void rotateZ(double z);

    public void translate(double x, double y, double z);

    public void scale(double x, double y, double z);

    public IntersectionInfo intersects(Ray r);
}
