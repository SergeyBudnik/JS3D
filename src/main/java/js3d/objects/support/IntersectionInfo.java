package js3d.objects.support;

import java.util.ArrayList;

public class IntersectionInfo {
    public ArrayList<SingleIntersectionInfo> allIOs;

    public IntersectionInfo(ArrayList<SingleIntersectionInfo> allIOs) {
        this.allIOs = allIOs;
    }

    public SingleIntersectionInfo getClosestIO() {
        return allIOs.get(0);
    }
}
