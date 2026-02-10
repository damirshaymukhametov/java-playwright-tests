package com.study.playwright.tests;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import com.study.playwright.config.HeadlessChromeOptions;
import com.study.playwright.pages.ContactPage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertAll;

@UsePlaywright(HeadlessChromeOptions.class)
public class ContactFormTest {

    @Test
    @DisplayName("Fill out the form test")
    void shouldFillTheForm(Page page) {
        ContactPage contact = new ContactPage(page).open()
                .fillForm("Bill", "Snow", "bill-snow@test.com",
                        "Hello, world!", "Warranty");

        assertAll(
                () -> Assertions.assertThat(contact.firstNameValue()).isEqualTo("Bill"),
                () -> Assertions.assertThat(contact.lastNameValue()).isEqualTo("Snow"),
                () -> Assertions.assertThat(contact.emailValue()).isEqualTo("bill-snow@test.com"),
                () -> Assertions.assertThat(contact.messageValue()).isEqualTo("Hello, world!"),
                () -> Assertions.assertThat(contact.subjectValue()).isEqualTo("warranty")
        );
    }

    @Test
    @DisplayName("Attach file to the form")
    void shouldAttachFile(Page page) throws URISyntaxException {
        Path attachment = Paths.get(
                ClassLoader.getSystemResource("data/test-data.txt").toURI()
        );

        ContactPage contact = new ContactPage(page).open()
                .attachFile(attachment);

        Assertions.assertThat(contact.uploadedFileName())
                .endsWith("test-data.txt");
    }
}