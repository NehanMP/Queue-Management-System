package com.Task2_and_Task3;

public final class Customer {
    private final String firstName;
    private final String lastName;
    private final int burgersRequired;

    public Customer(String firstName, String lastName, int burgersRequired) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.burgersRequired = burgersRequired;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public int burgersRequired() {
        return burgersRequired;
    }
}
