package lesson4.Main;

import lesson4.BubbleSort.BubbleSort;
import lesson4.FastSort.FastSort;
import lesson4.Person.Person;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        int begin =0, end = 10000;

        Person[] persons = new Person[end];
        persons = Person.generateArray(end);
        Person[] personsFast = persons.clone();
        Person[] personsBubble = persons.clone();
        /*persons[0] = new Person(81,"Иван", Person.Sex.MAN);
        persons[1] = new Person(24,"Элиза", Person.Sex.WOMAN);
        persons[2] = new Person(23,"Мария", Person.Sex.WOMAN);
        persons[3] = new Person(80,"Ян", Person.Sex.MAN);
        persons[4] = new Person(80,"Антон", Person.Sex.MAN);*/

        FastSort sort = new FastSort();
        long start = System.currentTimeMillis();
        sort.Sort(personsFast,begin,end);
        System.out.println("Быстрая сортировка - " + (System.currentTimeMillis() - start) + " ms");

        BubbleSort sort2 = new BubbleSort();
        start = System.currentTimeMillis();
        sort2.Sort(personsBubble,begin,end);
        System.out.println("Пузырьковая сортировка - " + (System.currentTimeMillis() - start) + " ms");
        printElements(persons,personsFast,personsBubble);
    }
    private static void printElements(Person[] persons, Person[] personsFast, Person[] personsBubble )
    {
        for (int i = 0; i < persons.length; i++) {
            System.out.print("[sex:"+persons[i].getSex().toString() + ";");
            System.out.print(" age:"+persons[i].getAge() + ";");
            System.out.print(" name:"+persons[i].getName() + "] | ");
            System.out.print("[sex:"+personsFast[i].getSex().toString() + ";");
            System.out.print(" age:"+personsFast[i].getAge() + ";");
            System.out.print(" name:"+personsFast[i].getName() + "] | ");
            System.out.print("[sex:"+personsBubble[i].getSex().toString() + ";");
            System.out.print(" age:"+personsBubble[i].getAge() + ";");
            System.out.print(" name:"+personsBubble[i].getName() + "] ");
            System.out.println();
        }

    }
}
