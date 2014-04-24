import js3d.loaders.ObjModelLoader;
import js3d.math.core.Vector;
import js3d.objects.core.SceneObject;
import js3d.objects.intangible.Ray;
import js3d.objects.primitives.Sphere;
import js3d.objects.primitives.Triangle;
import js3d.objects.support.IntersectionInfo;
import js3d.objects.support.Material;

import java.io.File;

public class JS3DTest {
    public static void main(String [] args) throws Exception {
        SceneObject s = new Triangle(new Vector(1, 1, 1), new Vector(2, 1, 1), new Vector(1, 2, 1), Material.GREEN_MIRROR);

        Ray r = new Ray(new Vector(1.2, 1.2, -5), new Vector(0, 0, 1));

        s.scale(2, 2, 2);

        IntersectionInfo io;

        if ((io = s.intersects(r)) != null) {
            System.out.println("YES");

            System.out.println(io.allIOs.size());
        } else {
            System.out.println("NO");
        }
    }
}
