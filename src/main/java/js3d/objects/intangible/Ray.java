package js3d.objects.intangible;

import js3d.math.core.Vector;

public class Ray {
    public Vector pos;
    public Vector dir;

    public Ray(Vector pos, Vector dir) {
        assert dir.len() == 1;

        this.pos = pos;
        this.dir = dir;
    }
}
