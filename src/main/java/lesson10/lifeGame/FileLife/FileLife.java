package lesson10.lifeGame.FileLife;

import lesson10.lifeGame.Point.Point;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class FileLife {
    /**
     * чтение файла, где хранится поле
     * @param path путь к файлу
     * @return начальное поле
     */
    public static Point[][] ReadFile(String path){
        StringBuilder str = new StringBuilder();
        try(FileReader reader = new FileReader(path)) {
            int charr;
            while ((charr = reader.read()) != -1){
                    str.append((char)charr);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        String[] linesField = str.toString().split("\\r\\n");
        Point[][] points = new Point[linesField.length][linesField.length];
        fillArray(points,linesField);
        return points;
    }

    private static void fillArray(Point[][] points, String[] linesField){
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                points[i][j] = linesField[i].charAt(j) == '1' ? new Point(true) : new Point();
            }
        }
    }

    /**
     * Запись результата в файл
     * @param path путь к файлу
     * @param points результат
     */
    public static void WriteFile(String path, Point[][] points){
        try(FileWriter writer = new FileWriter(path)) {
            for (int i = 0; i < points.length; i++) {
                for (int j = 0; j < points[i].length; j++) {
                    writer.write(points[i][j].getIsAlive() ? "\u220e " : "\u2219 ");
                }
                writer.write("\r\n");
            }
            writer.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
