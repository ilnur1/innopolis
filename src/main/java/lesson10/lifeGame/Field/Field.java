package lesson10.lifeGame.Field;

import lesson10.lifeGame.Point.Point;

import java.util.ArrayList;
import java.util.List;

public class Field {
    private Point[][] field;
    private Point[][] fieldNextGen;

    public Field(int size) {
        field = new Point[size][size];
        fieldNextGen = new Point[size][size];
    }

    public void startLife(Point[][] alivePoints, int countGeneration) {
        for (int i = 0; i < alivePoints.length; i++) {
            for (int j = 0; j < alivePoints[i].length; j++) {
                if (alivePoints[i][j] != null) {
                    field[i][j] = new Point(alivePoints[i][j].getIsAlive());
                    fieldNextGen[i][j] = new Point(alivePoints[i][j].getIsAlive());
                }
                else {
                    field[i][j] = new Point();
                    fieldNextGen[i][j] = new Point();
                }
            }

        }
        showField(field);
        for (int g = 0; g < countGeneration; g++) {
            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field[i].length; j++) {
                    List<Point> nearbyPoints = getNearbyPoints(field,i, j);
                    fieldNextGen[i][j].setIsAlive(field[i][j].isAliveNextGen(nearbyPoints));
                }
            }
            showField(fieldNextGen);
            field = fillField(fieldNextGen,field);
        }
    }

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
            }
            else if (i != 0 && i < field.length - 1 && j == 0) {
                errMes = "левый край";
                points.add(field[i-1][j]);
                points.add(field[i -1][j + 1]);
                points.add(field[i][j+1]);
                points.add(field[i + 1][j + 1]);
                points.add(field[i+1][j]);
            } else if (i == 0 && j != 0 && j < field[i].length - 1) {
                errMes = "верхний край";
                points.add(field[i][j-1]);
                points.add(field[i + 1][j - 1]);
                points.add(field[i + 1][j]);
                points.add(field[i + 1][j + 1]);
                points.add(field[i][j+1]);
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

    private Point[][] fillField(Point[][] from, Point[][] to){
        for (int i = 0; i < from.length; i++) {
            for (int j = 0; j < from[i].length; j++) {
                if(from[i][j].getIsAlive())
                    to[i][j] = new Point(true);
                else
                    to[i][j] = new Point();
            }

        }
        return to;
    }

    public void showField(Point[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j].getIsAlive() ? "\u220e " : "\u2219 ");// \u058e
            }
            System.out.println();
        }
        System.out.println("---------------------");
    }
}
