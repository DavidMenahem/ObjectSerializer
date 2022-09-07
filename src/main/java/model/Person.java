package model;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private int age;

    private Cat cat;

    private List<String> hobbies = new ArrayList<>();

    public Person(String name, int age, Cat cat) {
        this.name = name;
        this.age = age;
        this.cat = cat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void addHobby(String hobby){
        this.hobbies.add(hobby);
    }
}
