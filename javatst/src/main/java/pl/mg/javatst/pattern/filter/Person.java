package pl.mg.javatst.pattern.filter;

import java.util.Objects;

public class Person {

    private String name;
    private String gender;
    private String martialStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(gender, person.gender) &&
                Objects.equals(martialStatus, person.martialStatus);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, gender, martialStatus);
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMartialStatus() {
        return martialStatus;
    }

    public void setMartialStatus(String martialStatus) {
        this.martialStatus = martialStatus;
    }

    public Person(String name, String gender, String martialStatus) {

        this.name = name;
        this.gender = gender;
        this.martialStatus = martialStatus;
    }
}
