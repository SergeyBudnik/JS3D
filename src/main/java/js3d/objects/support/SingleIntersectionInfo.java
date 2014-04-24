package js3d.objects.support;

import js3d.math.core.Vector;

public class SingleIntersectionInfo {
    public Vector pos;
    public Vector normal;
    public double dist;
    public Material material;

    public SingleIntersectionInfo(Vector pos, Vector normal, double dist, Material material) {
        this.pos = pos;
        this.normal = normal;
        this.dist = dist;
        this.material = material;
    }
}
