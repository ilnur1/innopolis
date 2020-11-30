package lesson4.Person;

import lesson2.Node.Node;
import lesson4.NameAndAgeMatch.NameAndAgeMatch;

import java.util.Objects;
import java.util.Random;

public class Person implements Comparable {
    int age;
    String name;
    Sex sex;

    public Person(int age, String name, Sex sex) throws Exception {
        setAge(age);
        setName(name);
        setSex(sex);
    }

    public enum Sex{
        MAN,
        WOMAN
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) throws Exception {
        if(age < 0 || age > 100)
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
    private static Sex getRandomSex()
    {
        Random randomSex = new Random();
        return randomSex.nextInt(2) == 0 ? Sex.MAN : Sex.WOMAN;
    }
    private static String getRandomName(){
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

    @Override
    public int compareTo(Object o) {
        if(o instanceof Person)
        {
            Person person = (Person) o;
            if(name.equals(person.getName()) && age == person.getAge()) {
                try {
                    throw new NameAndAgeMatch();
                } catch (NameAndAgeMatch nameAndAgeMatch) {
                    nameAndAgeMatch.printStackTrace();
                }
            }
            if(sex != person.getSex()) {
                if (sex == Sex.MAN)
                    return -1;
                else
                    return 1;
            }
            else {
                if(age != person.getAge()){
                    if(age > person.getAge())
                        return -1;
                    else
                        return 1;
                }
                else {
                    if(name.compareTo(person.getName()) == 0)
                        return 0;
                    if(name.compareTo(person.getName()) > 0)
                        return 1;
                    else
                        return -1;
                }
            }
        }
        else
            throw new ClassCastException();
    }


}
