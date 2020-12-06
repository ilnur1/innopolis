package lesson4.Main;

import lesson4.BubbleSort.BubbleSort;
import lesson4.FastSort.FastSort;
import lesson4.Person.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        int begin = 0, end = 10000;

        ArrayList<Person> persons= new ArrayList<>();
        persons = generateArray(end);
        ArrayList<Person> personsFast = new ArrayList<>();
        ArrayList<Person> personsBubble = new ArrayList<>();
        persons.stream().forEach(x-> {personsFast.add(x); personsBubble.add(x);});

        FastSort sort = new FastSort();
        long start = System.currentTimeMillis();
        sort.sort(personsFast, begin, end);
        System.out.println("Быстрая сортировка - " + (System.currentTimeMillis() - start) + " ms");

        BubbleSort sort2 = new BubbleSort();
        start = System.currentTimeMillis();
        sort2.sort(personsBubble, begin, end);
        ArrayList<Person> resultBubbleSort = sort2.getResultSortList();
        System.out.println("Пузырьковая сортировка - " + (System.currentTimeMillis() - start) + " ms");
        printElements(persons, personsFast, resultBubbleSort);

        for (String error : sort.getErrorLog()) {
            System.out.println(error);
        }
        for (String error : sort2.getErrorLog()) {
            System.out.println(error);
        }
    }

    private static void printElements(ArrayList<Person> persons, ArrayList<Person> personsFast, ArrayList<Person> personsBubble) {
        for (int i = 0; i < persons.size(); i++) {
            System.out.print("[sex:" + persons.get(i).getSex().toString() + ";");
            System.out.print(" age:" + persons.get(i).getAge() + ";");
            System.out.print(" name:" + persons.get(i).getName() + "] | ");
            System.out.print("[sex:" + personsFast.get(i).getSex().toString() + ";");
            System.out.print(" age:" + personsFast.get(i).getAge() + ";");
            System.out.print(" name:" + personsFast.get(i).getName() + "] | ");
            System.out.print("[sex:" + personsBubble.get(i).getSex().toString() + ";");
            System.out.print(" age:" + personsBubble.get(i).getAge() + ";");
            System.out.print(" name:" + personsBubble.get(i).getName() + "] ");
            System.out.println();
        }

    }

    public static ArrayList<Person> generateArray(int size) throws Exception {
        ArrayList<Person> persons = new ArrayList<>();
        Random randomAge = new Random();
        for (int i = 0; i < size; i++) {
            persons.add(new Person(randomAge.nextInt(101),
                    getRandomName(),
                    getRandomSex()));
        }
        return persons;
    }

    private static Person.Sex getRandomSex() {
        Random randomSex = new Random();
        return randomSex.nextInt(2) == 0 ? Person.Sex.MAN : Person.Sex.WOMAN;
    }

    private static String getRandomName() {
        String alphabet = "абвгдежзиклмнопрстуфхцчшщъыьэюя";
        Random randomNameLength = new Random();
        randomNameLength.setSeed(2);
        int nameLength = randomNameLength.nextInt(10);
        Random randomChar = new Random();
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < nameLength; i++) {
            name.append(alphabet.charAt(randomChar.nextInt(31)));
        }
        return name.toString();
    }
}
