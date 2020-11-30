package lesson4.FastSort;

import lesson4.ISort.ISort;
import lesson4.Person.Person;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class FastSort implements ISort {
    @Override
    public void Sort(Person[] persons, int startIndex, int endIndex) {
        if (startIndex == endIndex)
            return;
        Stack<Person> before = new Stack<>();
        Stack<Person> after = new Stack<>();

        int separatorIndex = startIndex;
        Person separator = persons[separatorIndex];
        for (int i = startIndex; i < endIndex; i++) {
            if (separatorIndex == i ) continue;
            if(persons[i].compareTo(separator) >=0 )
                after.push(persons[i]);
            else
                before.push(persons[i]);
        }
        for (int i = startIndex; i < endIndex; i++) {
            if(!before.empty())
                persons[i] = before.pop();
            else if(separator != null) {
                persons[i] = separator;
                separatorIndex = i;
                separator = null;
            }
            else
                persons[i] = after.pop();
        }
        Sort(persons, startIndex, separatorIndex);
        Sort(persons, separatorIndex + 1, endIndex);
    }
}
