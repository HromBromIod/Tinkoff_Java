package edu.hw7.Task3;

import java.util.List;

public interface PersonDatabase {
    void add(Person person);

    void delete(int id);

    Person findByName(String name);

    Person findByAddress(String address);

    Person findByPhone(String phone);
}
