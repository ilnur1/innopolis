package lesson4.BubbleSort;

import lesson4.ISort.ISort;
import lesson4.NameAndAgeMatch.NameAndAgeMatch;
import lesson4.Person.Person;

import java.util.ArrayList;

public class BubbleSort implements ISort {

    ArrayList<String> ErrorLog;

    public BubbleSort() {
        ErrorLog = new ArrayList<>();
    }

    /**
     * возвращает список людей, у которых совпали имена и возраст
     *
     * @return список людей
     */
    public ArrayList<String> getErrorLog() {
        return ErrorLog;
    }

    /**
     * Сортировка пузырьком
     *
     * @param persons    входной массив
     * @param startIndex начало диапазона сортировки
     * @param endIndex   конец диапазона сортировки
     */
    @Override
    public void sort(Person[] persons, int startIndex, int endIndex) throws NameAndAgeMatch {
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = startIndex; i < endIndex - 1; i++) {
                try {
                    if (persons[i].compareTo(persons[i + 1]) > 0) {
                        Person cach = persons[i];
                        persons[i] = persons[i + 1];
                        persons[i + 1] = cach;
                        sorted = false;
                    }
                } catch (NameAndAgeMatch e) {
                    ErrorLog.add(e.getMessage() + ": " + persons[i].getName() + "; " + persons[i].getAge() + "; " + persons[i].getSex());
                }
            }
        }
    }
}
