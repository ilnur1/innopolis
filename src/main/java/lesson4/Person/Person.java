package lesson4.Person;

import lesson4.NameAndAgeMatch.NameAndAgeMatch;

import java.util.Random;

public class Person<T> implements Comparable<Person> {
    int age;
    String name;
    Sex sex;

    public Person(int age, String name, Sex sex) throws Exception {
        setAge(age);
        setName(name);
        setSex(sex);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws Exception {
        if (age < 0 || age > 100)
            throw new Exception("Диапозон возраста от 0 до 100");
        else
            this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @Override
    public int compareTo(Person o) throws NameAndAgeMatch {
        if (name.equals(o.getName()) && age == o.getAge()) {
            throw new NameAndAgeMatch();
        }
        if (sex != o.getSex()) {
            if (sex == Sex.MAN)
                return -1;
            else
                return 1;
        } else {
            if (age != o.getAge()) {
                if (age > o.getAge())
                    return -1;
                else
                    return 1;
            } else {
                if (name.compareTo(o.getName()) == 0)
                    return 0;
                if (name.compareTo(o.getName()) > 0)
                    return 1;
                else
                    return -1;
            }
        }
    }

    public enum Sex {
        MAN,
        WOMAN
    }


}
