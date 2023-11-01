package edu.hw4;

import java.util.HashSet;
import java.util.Set;

public class ValidationError {
    private final String errorMessage;

    public ValidationError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static Set<ValidationError> animalErrors(Animal animal) {
        Set<ValidationError> errors = new HashSet<>();
        if (animal.name() == null) {
            errors.add(new ValidationError("NULL name"));
        } else if (animal.name().isEmpty()) {
            errors.add(new ValidationError("EMPTY name"));
        }
        if (animal.type() == null) {
            errors.add(new ValidationError("NULL type"));
        }
        if (animal.sex() == null) {
            errors.add(new ValidationError("NULL sex"));
        }
        if (animal.age() < 0) {
            errors.add(new ValidationError("NEGATIVE age"));
        }
        if (animal.height() < 0) {
            errors.add(new ValidationError("NEGATIVE height"));
        }
        if (animal.weight() < 0) {
            errors.add(new ValidationError("NEGATIVE weight"));
        }
        return errors;
    }
}
