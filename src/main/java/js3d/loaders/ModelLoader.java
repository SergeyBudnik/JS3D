package js3d.loaders;

import js3d.objects.complex.Model;
import js3d.objects.support.Material;

import java.io.File;
import java.io.FileNotFoundException;

public interface ModelLoader {
    public Model load(File file, Material m) throws FileNotFoundException, WrongModelFileFormatException;
}
