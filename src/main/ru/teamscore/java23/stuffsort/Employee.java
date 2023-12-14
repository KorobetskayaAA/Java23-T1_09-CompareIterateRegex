package ru.teamscore.java23.stuffsort;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.UUID;

public class Employee {
    private final UUID id;
    private String name;
    private String surname;
    private String position;
    private int wage;

    public Employee(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    static final NumberFormat wageFormat = NumberFormat.getCurrencyInstance(new Locale("ru"));
    @Override
    public String toString() {
        return String.format("{%s} %s - %s %s (%s)", id, position, surname, name, wageFormat.format(wage));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getWage() {
        return wage;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }
}
