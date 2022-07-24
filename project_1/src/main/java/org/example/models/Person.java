package org.example.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Person {

    private int id;

    @NotNull(message = "Full name should not be empty")
    @Size(min = 3, max = 200, message = "Full name should be between 3 and 200 characters")
    private String fullName;

    @Min(value = 0, message = "Age should be greater than 0")
    private int age;

    public Person(int id, String full_name, int age) {
        this.id = id;
        this.fullName = full_name;
        this.age = age;
    }

    public Person() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String full_name) {
        this.fullName = full_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
