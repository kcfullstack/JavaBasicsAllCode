package com.kpc.java8.optional;

import java.util.Optional;

public class OptionalDemo {

    public void OptionalCaseScenarios(){

        // CASE 1: Creating Optionals
        Optional<String> emptyOpt = Optional.empty(); // Represents an absent value
        Optional<String> presentOpt = Optional.of("Hello Java"); // Requires non-null value (throws NPE if null)

        String nullableValue = null;
        Optional<String> nullableOpt = Optional.ofNullable(nullableValue); // Safe for nulls; results in an empty Optional

        // CASE 2: Fallback to a default value (orElse)
        // If empty, uses the provided fallback value
        String resultDefault = nullableOpt.orElse("Default Value");
        System.out.println("Fallback result: " + resultDefault); // Prints: Default Value

        // CASE 3: Fallback using a Supplier (orElseGet)
        // Better for performance if computing the default value is expensive
        String resultSupplier = nullableOpt.orElseGet(() -> "Computed Default Value");

        // CASE 4: Action when value is present (ifPresent)
        // Prints the value only if it exists; does nothing otherwise
        presentOpt.ifPresent(val -> System.out.println("Value is present: " + val));

        // CASE 5: Conditional logic for both states (ifPresentOrElse) - Java 9+
        nullableOpt.ifPresentOrElse(
                val -> System.out.println("Found: " + val),
                () -> System.out.println("Action executed because value is empty!")
        );

        // CASE 6: Transforming values safely (map)
        // Transforms the inner value if present, otherwise yields an empty Optional safely
        Optional<Integer> lengthOpt = presentOpt.map(String::length);
        lengthOpt.ifPresent(len -> System.out.println("String length: " + len)); // Prints: 10

        // CASE 7: Filtering values (filter)
        // Checks a predicate condition; turns empty if condition fails
        Optional<String> filteredOpt = presentOpt.filter(val -> val.contains("Java"));
        System.out.println("Is 'Java' matching filter present? " + filteredOpt.isPresent()); // true

        // CASE 8: Throwing an exception when absent (orElseThrow)
        try {
            nullableOpt.orElseThrow(() -> new IllegalArgumentException("Required data is missing!"));
        } catch (IllegalArgumentException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }


}
