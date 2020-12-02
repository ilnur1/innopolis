package lesson4.ISort;

import lesson4.NameAndAgeMatch.NameAndAgeMatch;
import lesson4.Person.Person;

import java.util.ArrayList;

public interface ISort {
    /**
     * Метод для сортировки
     * @param array входной массив
     * @param startIndex начало диапазона сортировки
     * @param endIndex конец диапазона сортировки
     */
    void sort(Person[] array, int startIndex, int endIndex);
}
