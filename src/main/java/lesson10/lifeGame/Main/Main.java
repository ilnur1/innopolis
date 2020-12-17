package lesson10.lifeGame.Main;

import lesson10.lifeGame.FileLife.FileLife;
import lesson10.lifeGame.Life.Life;
import lesson10.lifeGame.Point.Point;
import org.apache.commons.lang3.time.StopWatch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        Point[][] arr = FileLife.ReadFile("src\\main\\java\\lesson10\\lifeGame\\begin.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите количество генераций: ");
        int countGeneration = Integer.parseInt(reader.readLine());
        System.out.print("Введите количество потоков: ");
        int countThreads = Integer.parseInt(reader.readLine());
        System.out.print("Введите имя файла для сохранения результата: ");
        String name = reader.readLine();

        Life l = new Life(countThreads);
        StopWatch time = new StopWatch();
        time.start();
        Point[][] result = l.startLife(arr, countGeneration);
        time.stop();
        System.out.println(time.getTime() + " ms");
        FileLife.WriteFile("src\\main\\java\\lesson10\\lifeGame\\" + name + ".txt", result);
    }
}
