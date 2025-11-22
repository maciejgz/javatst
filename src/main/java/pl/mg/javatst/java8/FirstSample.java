package pl.mg.javatst.java8;


import java.io.File;
import java.io.FileFilter;

/**
 * Lambda tests
 */
public class FirstSample {


    public File[] findHiddenFiles7(String path) {
        File[] hiddenFiles = new File(path).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isHidden();
            }
        });
        return hiddenFiles;
    }


    public File[] findHiddenFiles8(String path) {
        File[] hiddenFiles = new File(path).listFiles(File::isHidden);
        return hiddenFiles;
    }

}
