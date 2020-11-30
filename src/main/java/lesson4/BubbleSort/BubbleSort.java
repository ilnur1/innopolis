package lesson4.BubbleSort;

import lesson4.ISort.ISort;
import lesson4.Person.Person;

public class BubbleSort implements ISort {
    @Override
    public void Sort(Person[] persons, int startIndex, int endIndex) {
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = startIndex; i < endIndex - 1; i++) {
                if (persons[i].compareTo(persons[i + 1]) > 0) {
                    Person cach = persons[i];
                    persons[i] = persons[i + 1];
                    persons[i + 1] = cach;
                    sorted = false;
                }
            }
        }
    }
}
