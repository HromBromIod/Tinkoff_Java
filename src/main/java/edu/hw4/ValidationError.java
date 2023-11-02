package edu.hw4;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidationError {
    protected final String errorMessage;

    public ValidationError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static Set<ValidationError> animalErrors(Animal animal) {
        Set<ValidationError> errors = new HashSet<>();
        if (animal.name() == null) {
            errors.add(new ValidationError("0: NULL name"));
        } else if (animal.name().isEmpty()) {
            errors.add(new ValidationError("6: EMPTY name"));
        }
        if (animal.type() == null) {
            errors.add(new ValidationError("1: NULL type"));
        }
        if (animal.sex() == null) {
            errors.add(new ValidationError("2: NULL sex"));
        }
        if (animal.age() < 0) {
            errors.add(new ValidationError("3: NEGATIVE age"));
        }
        if (animal.height() < 0) {
            errors.add(new ValidationError("4: NEGATIVE height"));
        }
        if (animal.weight() < 0) {
            errors.add(new ValidationError("5: NEGATIVE weight"));
        }
        return errors.stream().sorted(Comparator.comparing(o -> o.errorMessage))
            .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static String animalErrorsToString(Set<ValidationError> animalErrors) {
        StringBuilder errors = new StringBuilder();
        for (ValidationError o : animalErrors) {
            if (errors.isEmpty()) {
                errors.append(o.errorMessage);
            } else {
                errors.append(", ").append(o.errorMessage);
            }
        }
        errors.append(".");
        return errors.toString();
    }
}
