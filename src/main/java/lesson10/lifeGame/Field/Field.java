package lesson10.lifeGame.Field;

import lesson10.lifeGame.Point.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * класс описывает поле, где живут точки (Point)
 */
public class Field {
    public static Point[][] fieldNextGen;
    private Point[][] field;
    private Point[][] alivePoints;

    public Field(Point[][] alivePoints, int size) {
        field = new Point[size][size];
        fieldNextGen = new Point[size][size];
        this.alivePoints = alivePoints;
    }

    /**
     * Заполняет поле для следующих поколений точек
     *
     * @param start_i   начальный индекс строки
     * @param increment инкремент
     */
    public void fillFieldNextGen(int start_i, int increment) {
        for (int i = start_i; i < field.length; i += increment) {
            for (int j = 0; j < field[i].length; j++) {
                List<Point> nearbyPoints = getNearbyPoints(field, i, j);
                fieldNextGen[i][j].setIsAlive(field[i][j].isAliveNextGen(nearbyPoints));
            }
        }
    }

    /**
     * Заполняет начальное поле и инициализирует поле следующего поколения
     *
     * @param start_i   начальный индекс строки
     * @param increment инкремент
     */
    public void fillFirstFields(int start_i, int increment) {
        for (int i = start_i; i < alivePoints.length; i += increment) {
            for (int j = 0; j < alivePoints[i].length; j++) {
                if (alivePoints[i][j] != null) {
                    field[i][j] = new Point(alivePoints[i][j].getIsAlive());
                    fieldNextGen[i][j] = new Point();
                } else {
                    field[i][j] = new Point();
                    fieldNextGen[i][j] = new Point();
                }
            }
        }
    }

    /**
     * Возвращает список соседей точки(i,j)
     *
     * @param field поле где находится точка(i,j)
     * @param i     индекс строки
     * @param j     индекс столбца
     * @return список соседей
     */
    private List<Point> getNearbyPoints(Point[][] field, int i, int j) {
        List<Point> points = new ArrayList<>();
        String errMes = "";
        try {
            if (i == field.length - 1 && j == field[i].length - 1) {
                errMes = "правый нижгий угл";
                points.add(field[i][j - 1]);
                points.add(field[i - 1][j - 1]);
                points.add(field[i - 1][j]);
            } else if (i == 0 && j == 0) {
                errMes = "левый верхний угол";
                points.add(field[i + 1][j]);
                points.add(field[i + 1][j + 1]);
                points.add(field[i][j + 1]);
            } else if (i == field.length - 1 && j == 0) {
                errMes = "левый нижний угол";
                points.add(field[i - 1][j]);
                points.add(field[i - 1][j + 1]);
                points.add(field[i][j + 1]);
            } else if (i == 0 && j == field[i].length - 1) {
                errMes = "правый верхний угол";
                points.add(field[i][j - 1]);
                points.add(field[i + 1][j - 1]);
                points.add(field[i + 1][j]);
            } else if (i != 0 && i < field.length - 1 && j == 0) {
                errMes = "левый край";
                points.add(field[i - 1][j]);
                points.add(field[i - 1][j + 1]);
                points.add(field[i][j + 1]);
                points.add(field[i + 1][j + 1]);
                points.add(field[i + 1][j]);
            } else if (i == 0 && j != 0 && j < field[i].length - 1) {
                errMes = "верхний край";
                points.add(field[i][j - 1]);
                points.add(field[i + 1][j - 1]);
                points.add(field[i + 1][j]);
                points.add(field[i + 1][j + 1]);
                points.add(field[i][j + 1]);
            } else if (i != 0 && i < field.length - 1 && j == field[i].length - 1) {
                errMes = "правый край";
                points.add(field[i - 1][j - 1]);
                points.add(field[i][j - 1]);
                points.add(field[i + 1][j - 1]);
                points.add(field[i - 1][j]);
                points.add(field[i + 1][j]);
            } else if (i == field.length - 1 && j != 0 && j < field[i].length - 1) {
                errMes = "нижний край";
                points.add(field[i - 1][j - 1]);
                points.add(field[i][j - 1]);
                points.add(field[i - 1][j]);
                points.add(field[i - 1][j + 1]);
                points.add(field[i][j + 1]);
            } else {
                errMes = "центр";
                points.add(field[i - 1][j - 1]);
                points.add(field[i][j - 1]);
                points.add(field[i + 1][j - 1]);
                points.add(field[i - 1][j]);
                points.add(field[i + 1][j]);
                points.add(field[i - 1][j + 1]);
                points.add(field[i][j + 1]);
                points.add(field[i + 1][j + 1]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println(errMes + ": " + i + " " + j);
        }
        return points;
    }

    /**
     * Выводит состояние поле на экран
     *
     * @param field поле
     */
    public void showField(Point[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j].getIsAlive() ? "\u220e " : "\u2219 ");
            }
            System.out.println();
        }
        System.out.println("---------------------");
    }
}
