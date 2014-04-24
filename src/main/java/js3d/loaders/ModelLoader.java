package js3d.loaders;

import js3d.objects.complex.Model;

import java.io.File;
import java.io.FileNotFoundException;

public interface ModelLoader {
    public Model load(File file) throws FileNotFoundException, WrongModelFileFormatException;
}
