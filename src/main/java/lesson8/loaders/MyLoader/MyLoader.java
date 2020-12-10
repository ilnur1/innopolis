package lesson8.loaders.MyLoader;

import lesson8.loaders.Main.Main;

import java.io.*;

public class MyLoader extends ClassLoader {
    public final static String pathSomeClass = "lesson8.loaders.SomeClass.SomeClass";

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (pathSomeClass.equals(name)) {
            return findClass(name);
        }
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (pathSomeClass.equals(name)) {
            try {
                File file = new File(Main.classPath + ".class");
                InputStream input = new BufferedInputStream(new FileInputStream(file));
                byte[] arrBytes = new byte[(int) file.length()];
                input.read(arrBytes);
                return defineClass(name, arrBytes, 0, arrBytes.length);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.findClass(name);
    }
}
