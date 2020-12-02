package lesson4.FastSort;

import lesson4.ISort.ISort;
import lesson4.NameAndAgeMatch.NameAndAgeMatch;
import lesson4.Person.Person;

import java.util.ArrayList;
import java.util.Stack;

public class FastSort implements ISort {

    ArrayList<String> ErrorLog;

    public FastSort() {
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
     * Быстрая сортировка
     *
     * @param persons    входной массив
     * @param startIndex начало диапазона сортировки
     * @param endIndex   конец диапазона сортировки
     */
    @Override
    public void sort(Person[] persons, int startIndex, int endIndex) throws NameAndAgeMatch {
        if (startIndex == endIndex)
            return;
        Stack<Person> before = new Stack<>();
        Stack<Person> after = new Stack<>();

        int separatorIndex = startIndex;
        Person separator = persons[separatorIndex];
        for (int i = startIndex; i < endIndex; i++) {
            if (separatorIndex == i) continue;
            try {
                if (persons[i].compareTo(separator) >= 0)
                    after.push(persons[i]);
                else
                    before.push(persons[i]);
            } catch (NameAndAgeMatch e) {
                ErrorLog.add(e.getMessage() + ": " + persons[i].getName() + "; " + persons[i].getAge() + "; " + persons[i].getSex());
            }
        }
        for (int i = startIndex; i < endIndex; i++) {
            if (!before.empty())
                persons[i] = before.pop();
            else if (separator != null) {
                persons[i] = separator;
                separatorIndex = i;
                separator = null;
            } else if (!after.empty())
                persons[i] = after.pop();
        }
        sort(persons, startIndex, separatorIndex);
        sort(persons, separatorIndex + 1, endIndex);
    }
}
