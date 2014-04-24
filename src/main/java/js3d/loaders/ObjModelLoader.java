package js3d.loaders;

import js3d.math.core.Vector;
import js3d.objects.complex.Model;
import js3d.objects.primitives.Triangle;
import js3d.objects.support.Material;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ObjModelLoader implements ModelLoader {
    @Override
    public Model load(File file) throws FileNotFoundException, WrongModelFileFormatException {
        List<Vector> vertices = new ArrayList<>();
        List<Vector> normals = new ArrayList<>();
        List<Triangle> triangles = new ArrayList<>();

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();

            if (line.isEmpty()) {
                continue;
            }

            String [] lineParts = line.split("\\s+");

            switch (lineParts[0]) {
                case "v":
                    addVertex(vertices, lineParts);
                    break;
                case "vn":
                    addNormal(normals, lineParts);
                    break;
                case "f":
                    addTriangle(triangles, lineParts, normals, vertices);
                    break;
            }
        }

        return new Model(triangles);
    }

    private void addVertex(List<Vector> vertices, String [] lineParts) throws WrongModelFileFormatException {
        try {
            if (lineParts.length < 4) {
                throw new WrongModelFileFormatException();
            }

            double x = Double.parseDouble(lineParts[1]);
            double y = Double.parseDouble(lineParts[2]);
            double z = Double.parseDouble(lineParts[3]);

            vertices.add(new Vector(x, y, z));
        } catch (NumberFormatException e) {
            throw new WrongModelFileFormatException();
        }
    }

    private void addNormal(List<Vector> normals, String [] lineParts) throws WrongModelFileFormatException {
        try {
            if (lineParts.length < 4) {
                throw new WrongModelFileFormatException();
            }

            double x = Double.parseDouble(lineParts[1]);
            double y = Double.parseDouble(lineParts[2]);
            double z = Double.parseDouble(lineParts[3]);

            normals.add(new Vector(x, y, z));
        } catch (NumberFormatException e) {
            throw new WrongModelFileFormatException();
        }
    }

    private void addTriangle(List<Triangle> triangles, String [] lineParts, List<Vector> normals, List<Vector> vertices)
            throws WrongModelFileFormatException {

        try {
            if (lineParts.length != 4) {
                throw new WrongModelFileFormatException();
            }

            int [] vi1 = getVertexInfo(lineParts[1]);
            int [] vi2 = getVertexInfo(lineParts[2]);
            int [] vi3 = getVertexInfo(lineParts[3]);

            Triangle t = new Triangle(vertices.get(vi1[0]), vertices.get(vi2[0]), vertices.get(vi3[0]), Material.BLUE_MIRROR);

            /* ToDo: Add textures support */
            /* ToDo: Add normals support */
            /* ToDo: Add material support */

            triangles.add(t);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new WrongModelFileFormatException();
        }
    }

    private int [] getVertexInfo(String linePart) throws WrongModelFileFormatException {

        try {
            String [] vertexInfoParts = linePart.split("/");

            if (vertexInfoParts.length == 0) {
                throw new WrongModelFileFormatException();
            }

            vertexInfoParts[0] = vertexInfoParts[0].trim();

            if (vertexInfoParts[0].isEmpty()) {
                throw new WrongModelFileFormatException();
            }

            int vIndex = Integer.parseInt(vertexInfoParts[0]);

            return new int [] {vIndex - 1, -1, -1};
        } catch (NumberFormatException e) {
            throw new WrongModelFileFormatException();
        }
    }
}
