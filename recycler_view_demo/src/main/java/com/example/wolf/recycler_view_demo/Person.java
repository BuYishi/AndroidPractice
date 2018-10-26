package com.example.wolf.recycler_view_demo;

public class Person {
    private int portraitResId;
    private String name;
    private String gender;
    private int age;
    private String description;

    public Person(int portraitResId, String name, String gender, int age, String description) {
        this.portraitResId = portraitResId;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.description = description;
    }
    public int getPortraitResId() {
        return portraitResId;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getDescription() {
        return description;
    }
}