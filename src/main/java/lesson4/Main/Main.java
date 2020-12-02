package lesson4.Main;

import lesson4.BubbleSort.BubbleSort;
import lesson4.FastSort.FastSort;
import lesson4.Person.Person;

import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        int begin = 0, end = 10000;

        Person[] persons = new Person[end];
        persons = generateArray(end);
        Person[] personsFast = persons.clone();
        Person[] personsBubble = persons.clone();

        FastSort sort = new FastSort();
        long start = System.currentTimeMillis();
        sort.sort(personsFast, begin, end);
        System.out.println("Быстрая сортировка - " + (System.currentTimeMillis() - start) + " ms");

        BubbleSort sort2 = new BubbleSort();
        start = System.currentTimeMillis();
        sort2.sort(personsBubble, begin, end);
        System.out.println("Пузырьковая сортировка - " + (System.currentTimeMillis() - start) + " ms");
        printElements(persons, personsFast, personsBubble);

        for (String error : sort.getErrorLog()) {
            System.out.println(error);
        }
        for (String error : sort2.getErrorLog()) {
            System.out.println(error);
        }
    }

    private static void printElements(Person[] persons, Person[] personsFast, Person[] personsBubble) {
        for (int i = 0; i < persons.length; i++) {
            System.out.print("[sex:" + persons[i].getSex().toString() + ";");
            System.out.print(" age:" + persons[i].getAge() + ";");
            System.out.print(" name:" + persons[i].getName() + "] | ");
            System.out.print("[sex:" + personsFast[i].getSex().toString() + ";");
            System.out.print(" age:" + personsFast[i].getAge() + ";");
            System.out.print(" name:" + personsFast[i].getName() + "] | ");
            System.out.print("[sex:" + personsBubble[i].getSex().toString() + ";");
            System.out.print(" age:" + personsBubble[i].getAge() + ";");
            System.out.print(" name:" + personsBubble[i].getName() + "] ");
            System.out.println();
        }

    }

    public static Person[] generateArray(int size) throws Exception {
        Person[] persons = new Person[size];
        Random randomAge = new Random();
        for (int i = 0; i < persons.length; i++) {
            persons[i] = new Person(randomAge.nextInt(101),
                    getRandomName(),
                    getRandomSex());
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
