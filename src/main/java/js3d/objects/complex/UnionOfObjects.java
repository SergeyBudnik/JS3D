package js3d.objects.complex;

import js3d.objects.core.ClosedObject;
import js3d.objects.intangible.Ray;
import js3d.objects.support.IntersectionInfo;
import js3d.objects.support.SingleIntersectionInfo;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class UnionOfObjects extends ComplexObject {
    public UnionOfObjects(ClosedObject o1, ClosedObject o2) {
        super(o1, o2);
    }

    @Override
    public IntersectionInfo intersects(Ray r) {
        IntersectionInfo io1 = o1.intersects(r);
        IntersectionInfo io2 = o2.intersects(r);

        if (io1 == null) {
            return io2;
        } else if (io2 == null) {
            return io1;
        } else {
            return getUnion(io1, io2);
        }
    }

    private IntersectionInfo getUnion(IntersectionInfo io1, IntersectionInfo io2) {
        ArrayList<SingleIntersectionInfo> allIOs = new ArrayList<>();

        int current = -1;

        int i, j;

        for (i = 0, j = 0; i < io1.allIOs.size() && j < io2.allIOs.size();) {
            SingleIntersectionInfo sio1 = io1.allIOs.get(i);
            SingleIntersectionInfo sio2 = io2.allIOs.get(j);

            if (current == -1) {
                if (sio1.dist < sio2.dist) {
                    current = 1;

                    allIOs.add(sio1);
                } else {
                    current = 2;

                    allIOs.add(sio2);
                }
            } else if (current == 1) {
                if (sio1.dist < sio2.dist) {
                    current = -1;

                    allIOs.add(sio1);
                } else {
                    current = 12;
                }
            } else if (current == 2) {
                if (sio2.dist < sio1.dist) {
                    current = -1;

                    allIOs.add(sio2);
                } else {
                    current = 21;
                }
            } else if (current == 12) {
                if (sio1.dist < sio2.dist) {
                    current = 2;
                } else {
                    current = 1;
                }
            } else {
                if (sio2.dist < sio1.dist) {
                    current = 1;
                } else {
                    current = 2;
                }
            }

            if (sio1.dist < sio2.dist) {
                i++;
            } else {
                j++;
            }
        }

        for (; i < io1.allIOs.size(); i++) {
            allIOs.add(io1.allIOs.get(i));
        }

        for (; j < io2.allIOs.size(); j++) {
            allIOs.add(io2.allIOs.get(j));
        }

        if (allIOs.size() == 0) {
            return null;
        }

        return new IntersectionInfo(allIOs);
    }
}
