package lesson4.ISort;

import lesson4.NameAndAgeMatch.NameAndAgeMatch;
import lesson4.Person.Person;

import java.util.ArrayList;

public interface ISort {
    /**
     * Метод для сортировки
     * @param persons входной лист
     * @param startIndex начало диапазона сортировки
     * @param endIndex конец диапазона сортировки
     */
    void sort(ArrayList<Person> persons, int startIndex, int endIndex);
}
