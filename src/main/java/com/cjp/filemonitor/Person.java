package com.cjp.filemonitor;

public class Person {
    private String firstname;
    private Integer age;

    public Person(final String firstname, final Integer age) {
        this.firstname = firstname;
        this.age = age;
    }

    public String getFirstname() {
        return firstname;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return firstname + ", " + age + "ans";

    }

}
