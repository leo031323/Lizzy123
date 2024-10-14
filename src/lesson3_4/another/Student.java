package lesson3_4.another;

import lesson3_4.one.Person;

import java.util.Objects;

public class Student extends Person {
    private String number;

    public Student(String name, int age) {
        super(name, age);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Objects.equals(number, student.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), number);
    }
}
