package lesson10.lifeGame.Point;

import java.util.List;

/**
 * класс описывает точку на поле
 */
public class Point {
    private boolean isAlive;

    public Point() {
        isAlive = false;
    }

    public Point(boolean isAlive) {
        setIsAlive(isAlive);
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public void setIsAlive(boolean alive) {
        isAlive = alive;
    }

    /**
     * Возвращает значение жизни для следующего поколения
     *
     * @param listPoint список соседних точек
     * @return значение жизни
     */
    public boolean isAliveNextGen(List<Point> listPoint) {
        int countAlive = (int) listPoint.stream().filter(x -> x.getIsAlive()).count();
        if (isAlive) {
            if (countAlive == 2 || countAlive == 3)
                return true;
            else
                return false;
        } else {
            if (countAlive == 3)
                return true;
            else
                return false;
        }
    }
}
