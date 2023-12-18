package edu.hw7.Task3;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@SuppressWarnings("MagicNumber")
public class ReadWriteLockedPersonDatabase implements PersonDatabase {
    private static final ReadWriteLock lock = new ReentrantReadWriteLock();
    private static final Map<Integer, Person> idMap = new HashMap<>();
    private static final Map<String, Integer> nameMap = new HashMap<>();
    private static final Map<String, Integer> addressMap = new HashMap<>();
    private static final Map<String, Integer> phoneMap = new HashMap<>();

    public int size() {
        return idMap.size();
    }

    public void add(Person person) throws InterruptedException {
        if (lock.writeLock().tryLock(100, TimeUnit.MILLISECONDS)) {
            try {
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
            } finally {
                lock.writeLock().unlock();
            }
        }
    }

    public void delete(int id) throws InterruptedException {
        if (lock.writeLock().tryLock(100, TimeUnit.MILLISECONDS)) {
            try {
                if (idMap.containsKey(id)) {
                    Person person = idMap.remove(id);
                    nameMap.remove(person.name());
                    addressMap.remove(person.address());
                    phoneMap.remove(person.phoneNumber());
                }
            } finally {
                lock.writeLock().unlock();
            }
        }
    }

    public Person findByName(String name) throws InterruptedException {
        if (lock.readLock().tryLock(100, TimeUnit.MILLISECONDS)) {
            try {
                if (nameMap.containsKey(name)
                    && phoneMap.containsValue(nameMap.get(name))
                    && addressMap.containsValue(nameMap.get(name))) {
                    return idMap.get(nameMap.get(name));
                }
            } finally {
                lock.readLock().unlock();
            }
        }
        return null;
    }

    public Person findByAddress(String address) throws InterruptedException {
        if (lock.readLock().tryLock(100, TimeUnit.MILLISECONDS)) {
            try {
                if (addressMap.containsKey(address)
                    && nameMap.containsValue(addressMap.get(address))
                    && phoneMap.containsValue(addressMap.get(address))) {
                    return idMap.get(addressMap.get(address));
                }
            } finally {
                lock.readLock().unlock();
            }
        }
        return null;
    }

    public Person findByPhone(String phone) throws InterruptedException {
        if (lock.readLock().tryLock(100, TimeUnit.MILLISECONDS)) {
            try {
                if (phoneMap.containsKey(phone)
                    && nameMap.containsValue(phoneMap.get(phone))
                    && addressMap.containsValue(phoneMap.get(phone))) {
                    return idMap.get(phoneMap.get(phone));
                }
            } finally {
                lock.readLock().unlock();
            }
        }
        return null;
    }
}
