package lesson10.lifeGame.Life;

import lesson10.lifeGame.Point.Point;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

public class LifeTest {

    public void start(int size, int countThread, int countGeneration) throws ExecutionException, InterruptedException {
        Point[][] arr = new Point[size][size];
        arr[0][2] = new Point(true);
        arr[1][2] = new Point(true);
        arr[2][2] = new Point(true);
        arr[2][1] = new Point(true);
        arr[1][0] = new Point(true);
        Life life = new Life(countThread);
        life.startLife(arr,countGeneration);
    }

    @Test
    public void size100() throws ExecutionException, InterruptedException {
        int countThread = 1;
        int size = 100;
        int countGeneration = 10;
        StopWatch time = new StopWatch();
        time.start();
        start(size,countThread,countGeneration);
        time.stop();
        System.out.println("Один поток: " + time.getTime() + " ms");

        countThread = 2;
        time.reset();
        time.start();
        start(size,countThread,countGeneration);
        time.stop();
        System.out.println("Два потока: " + time.getTime() + " ms");

        countThread = 3;
        time.reset();
        time.start();
        start(size,countThread,countGeneration);
        time.stop();
        System.out.println("Три потока: " + time.getTime() + " ms");
    }

    @Test
    public void size1000() throws ExecutionException, InterruptedException {
        int countThread = 1;
        int size = 1000;
        int countGeneration = 10;
        StopWatch time = new StopWatch();

        time.start();
        start(size,countThread,countGeneration);
        time.stop();
        System.out.println("Один поток: " + time.getTime() + " ms");

        countThread = 2;
        time.reset();
        time.start();
        start(size,countThread,countGeneration);
        time.stop();
        System.out.println("Два потока: " + time.getTime() + " ms");

        countThread = 3;
        time.reset();
        time.start();
        start(size,countThread,countGeneration);
        time.stop();
        System.out.println("Три потока: " + time.getTime() + " ms");


    }

    @Test
    public void size3000() throws ExecutionException, InterruptedException {
        int countThread = 1;
        int size = 1000;
        int countGeneration = 10;
        StopWatch time = new StopWatch();
        time.start();
        start(size,countThread,countGeneration);
        time.stop();
        System.out.println("Один поток: " + time.getTime() + " ms");

        countThread = 2;
        time.reset();
        time.start();
        start(size,countThread,countGeneration);
        time.stop();
        System.out.println("Два потока: " + time.getTime() + " ms");

        countThread = 3;
        time.reset();
        time.start();
        start(size,countThread,countGeneration);
        time.stop();
        System.out.println("Три потока: " + time.getTime() + " ms");
    }

}