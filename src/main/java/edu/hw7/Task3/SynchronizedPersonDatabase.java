package edu.hw7.Task3;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ConstantName")
public class SynchronizedPersonDatabase implements PersonDatabase {
    private static final Map<Integer, Person> idMap = new HashMap<>();
    private static final Map<String, Integer> nameMap = new HashMap<>();
    private static final Map<String, Integer> addressMap = new HashMap<>();
    private static final Map<String, Integer> phoneMap = new HashMap<>();

    public int size() {
        return idMap.size();
    }

    @Override
    public synchronized void add(Person person) {
        idMap.put(person.id(), person);
        if (person.name() != null) {
            nameMap.put(person.name(), person.id());
        }
        if (person.address() != null) {
            addressMap.put(person.address(), person.id());
        }
        if (person.phoneNumber() != null) {
            phoneMap.put(person.phoneNumber(), person.id());
        }
    }

    @Override
    public synchronized void delete(int id) {
        if (idMap.containsKey(id)) {
            Person person = idMap.remove(id);
            nameMap.remove(person.name());
            addressMap.remove(person.address());
            phoneMap.remove(person.phoneNumber());
        }
    }

    @Override
    public Person findByName(String name) {
        if (nameMap.containsKey(name)
            && addressMap.containsValue(nameMap.get(name))
            && nameMap.containsValue(nameMap.get(name))) {
            return idMap.get(nameMap.get(name));
        }
        return null;
    }

    @Override
    public Person findByAddress(String address) {
        if (addressMap.containsKey(address)
            && addressMap.containsValue(addressMap.get(address))
            && addressMap.containsValue(addressMap.get(address))) {
            return idMap.get(addressMap.get(address));
        }
        return null;
    }

    @Override
    public Person findByPhone(String phone) {
        if (phoneMap.containsKey(phone)
            && phoneMap.containsValue(phoneMap.get(phone))
            && phoneMap.containsValue(phoneMap.get(phone))) {
            return idMap.get(phoneMap.get(phone));
        }
        return null;
    }
}
