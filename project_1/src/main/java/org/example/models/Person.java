package org.example.models;

import javax.validation.constraints.Pattern;

public class Person {

    private int person_id;

    @Pattern(regexp = "[A-ZА-ЯЁ][a-zа-яё]+ [A-ZА-ЯЁ][a-zа-яё]+ [A-ZА-ЯЁ][a-zа-яё]+",
             message = "Your full name should be in this format: Lastname Firstname Patronymic")
    private String fullName;

    private int year;

    public Person(int person_id, String fullName, int year) {
        this.person_id = person_id;
        this.fullName = fullName;
        this.year = year;
    }

    public Person() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int id) {
        this.person_id = id;
    }
}
