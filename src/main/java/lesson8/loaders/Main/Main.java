package lesson8.loaders.Main;

import lesson8.loaders.MyLoader.MyLoader;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    /**
     * В случе необходимости нужно исправить путь к файлу
     */
    public static String classPath = "C:\\Users\\user\\IdeaProjects\\Innopolis\\src\\main\\java\\lesson8\\loaders\\SomeClass\\SomeClass";

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, InterruptedException {

        FileOutputStream file = new FileOutputStream(classPath + ".java");
        String classBody = getClassBody();
        file.write(classBody.getBytes());
        file.flush();
        file.close();
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, classPath + ".java");
        MyLoader loader = new MyLoader();
        Class<?> clazz = loader.loadClass(MyLoader.pathSomeClass);
        Object obj = clazz.getDeclaredConstructor().newInstance();
        Method method = clazz.getMethod("doWork");
        method.invoke(obj);
    }

    private static String getClassBody() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder methodBody = new StringBuilder();
        while (true) {
            String line = reader.readLine();
            if (line.equals(""))
                break;
            methodBody.append(line);
        }
        return "package lesson8.loaders.SomeClass;\n" +
                "\n" +
                "import lesson8.loaders.Worker.Worker;\n" +
                "\n" +
                "public class SomeClass implements Worker {\n" +
                "    @Override\n" +
                "    public void doWork() {\n" +
                methodBody + "\n" +
                "    }\n" +
                "}";
    }
}
