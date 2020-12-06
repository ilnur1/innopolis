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
     * @param persons    входной лист
     * @param startIndex начало диапазона сортировки
     * @param endIndex   конец диапазона сортировки
     */
    @Override
    public void sort(ArrayList<Person> persons, int startIndex, int endIndex) throws NameAndAgeMatch {
        if (startIndex == endIndex)
            return;
        Stack<Person> before = new Stack<>();
        Stack<Person> after = new Stack<>();
        int separatorIndex = 0;
        Person separator = persons.get(startIndex);
        Person finalSeparator = separator;
        persons.subList(startIndex, endIndex).stream().forEach(x -> {
            if (!x.equals(finalSeparator)) {
                try {
                    if (x.compareTo(finalSeparator) >= 0)
                        after.push(x);
                    else
                        before.push(x);
                } catch (NameAndAgeMatch e) {
                    ErrorLog.add(e.getMessage() + ": " + x.getName() + "; " + x.getAge() + "; " + x.getSex());
                }
            }
        });
        boolean isNotInputSeparator = true;
        for (int i = startIndex; i < endIndex; i++) {
            if (!before.empty())
                persons.set(i, before.pop());
            else if (isNotInputSeparator) {
                persons.set(i, finalSeparator);
                separatorIndex = i;
                isNotInputSeparator = false;
            } else if (!after.empty())
                persons.set(i, after.pop());
        }

        sort(persons, startIndex, separatorIndex);
        sort(persons, separatorIndex + 1, endIndex);
    }


}
