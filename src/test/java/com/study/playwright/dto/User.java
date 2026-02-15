package com.study.playwright.dto;

import net.datafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public record User(
        String first_name,
        String last_name,
        List<String> address,
        String phone,
        String dob,
        String password,
        String email) {
    public static User randomUser() {
        Faker fake = new Faker();

        int year = fake.number().numberBetween(1970, 2000);
        int month = fake.number().numberBetween(1, 12);
        int day = fake.number().numberBetween(1, 28);
        LocalDate date = LocalDate.of(year, month, day);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        return new User(
                fake.name().firstName(),
                fake.name().lastName(),
                List.of(
                        fake.address().streetName(),
                        fake.address().city(),
                        fake.address().state(),
                        fake.address().country()
                ),
                fake.phoneNumber().phoneNumber(),
                formattedDate,
                "Xa765!&qwe",
                fake.internet().emailAddress()
        );
    }

    public User withPassword(String password) {
        return new User(
                first_name,
                last_name,
                address,
                phone,
                dob,
                password,
                email);
    }

    public User withFirstName(String first_name) {
        return new User(
                first_name,
                last_name,
                address,
                phone,
                dob,
                password,
                email);
    }
}
