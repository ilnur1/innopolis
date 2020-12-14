package lesson10.lifeGame.Point;

import lesson10.lifeGame.SetNextGenException.SetNextGenException;

import java.util.List;

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

    public boolean isAliveNextGen(List<Point> listPoint){
        int countAlive = (int) listPoint.stream().filter(x -> x.getIsAlive()).count();
        if(isAlive) {
            if (countAlive == 2 || countAlive == 3)
                return true;
            else
                return false;
        }
        else {
            if (countAlive == 3)
                return true;
            else
                return false;
        }
    }
}
