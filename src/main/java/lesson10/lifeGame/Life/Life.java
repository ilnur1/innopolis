package lesson10.lifeGame.Life;

import lesson10.lifeGame.Field.Field;
import lesson10.lifeGame.Point.Point;

import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * класс описывает жизнь, которая происходит на поле
 */
public class Life {

    private ThreadPoolExecutor executor;
    private LinkedList<Future> futures;
    private int countThreads;

    public Life(int countThreads) {
        this.countThreads = countThreads;
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(countThreads);
        futures = new LinkedList<>();
    }

    /**
     * запускает жизнь на поле
     *
     * @param points          начальные координаты живых точек
     * @param countGeneration количество генераций поколений
     * @return конечный результат
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public Point[][] startLife(Point[][] points, int countGeneration) throws ExecutionException, InterruptedException {
        Point[][] genertion = points;
        for (int g = 0; g < countGeneration; g++) {
            Field field = new Field(genertion, points.length);
            field.fillFirstFields(0, 1);
            for (int i = 0; i < countThreads; i++) {
                int startIndex = i;
                futures.add(executor.submit(() -> {
                    field.fillFieldNextGen(startIndex, countThreads);
                }));
            }
            for (Future future : futures) {
                future.get();
            }
            genertion = field.fieldNextGen;
        }
        executor.shutdown();
        return genertion;
    }

}
