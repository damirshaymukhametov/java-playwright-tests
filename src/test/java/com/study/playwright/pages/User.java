package com.study.playwright.pages;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;
import java.util.UUID;

public class User {

    @SerializedName("first_name")
    private final String firstName;

    @SerializedName("last_name")
    private final String lastName;

    @SerializedName("email")
    private final String email;

    @SerializedName("password")
    private final String password;

    private User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public static User randomUser() {
        String uniqueSuffix = UUID.randomUUID().toString().substring(0, 8);
        return new User(
                "John",
                "Doe",
                "john.doe+" + uniqueSuffix + "@example.com",
                "Password123!"
        );
    }

    public User withFirstName(String firstName) {
        return new User(firstName, this.lastName, this.email, this.password);
    }

    public User withPassword(String password) {
        return new User(this.firstName, this.lastName, this.email, password);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(firstName, user.firstName)
                && Objects.equals(lastName, user.lastName)
                && Objects.equals(email, user.email)
                && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, password);
    }
}

