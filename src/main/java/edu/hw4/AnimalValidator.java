package edu.hw4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class AnimalValidator {
    private AnimalValidator() {
    }

    public static List<ValidationError> validate(Animal animal) {
        List<ValidationError> errors = new ArrayList<>();
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
        errors.sort(Comparator.comparing(o -> o.errorMessage));
        return errors;
    }

    public static class ValidationError {
        protected final String errorMessage;

        public ValidationError(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public static String animalErrorsToString(List<ValidationError> animalErrors) {
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
}
