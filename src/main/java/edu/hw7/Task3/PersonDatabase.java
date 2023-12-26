package edu.hw7.Task3;

public interface PersonDatabase {
    void add(Person person) throws InterruptedException;

    void delete(int id) throws InterruptedException;

    Person findByName(String name) throws InterruptedException;

    Person findByAddress(String address) throws InterruptedException;

    Person findByPhone(String phone) throws InterruptedException;
}
