package lesson10.lifeGame.Main;

import lesson10.lifeGame.Field.Field;
import lesson10.lifeGame.Point.Point;

public class Main {
    public static void main(String[] args) {
        Field field = new Field(10);
        Point[][] arr = new Point[10][10];
        arr[0][2] = new Point(true);
        arr[1][2] = new Point(true);
        arr[2][2] = new Point(true);
        arr[2][1] = new Point(true);
        arr[1][0] = new Point(true);
        /*arr[3][3] = new Point(true);
        arr[3][4] = new Point(true);
        arr[3][5] = new Point(true);*/
        field.startLife(arr,35);
    }
}
