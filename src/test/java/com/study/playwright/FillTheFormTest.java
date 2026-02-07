package com.study.playwright;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.*;
import org.assertj.core.api.Assertions;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertAll;

@UsePlaywright(HeadlessChromeOptions.class)
public class FillTheFormTest {

    @DisplayName("Interacting with text fields")
    @Nested
    class WhenInteractingWithTextFields {

        @BeforeEach
        void openContactPage(Page page) {
            page.navigate("https://practicesoftwaretesting.com/contact");
        }

        @DisplayName("Attach the file to  the form")
        @Test
        void attachTheFile(Page page) throws URISyntaxException {
            var attachmentField = page.getByLabel("Attachment");
            Path attachment = Paths.get(ClassLoader.getSystemResource("data/test-data.txt").toURI());

            page.setInputFiles("#attachment", attachment);
            String uploadedFile = attachmentField.inputValue();

            org.assertj.core.api.Assertions.assertThat(uploadedFile).endsWith("test-data.txt");
        }

        @DisplayName("Fill the form")
        @Test
        void fillTheForm(Page page) {
            var firstNameField = page.getByLabel("First name");
            var lastNameField = page.getByLabel("Last name");
            var emailNameField = page.getByLabel("Email");
            var messageField = page.getByLabel("Message");
            var subjectField = page.getByLabel("Subject");

            firstNameField.fill("Bill");
            lastNameField.fill("Snow");
            emailNameField.fill("bill-snow@test.com");
            messageField.fill("Hello, world!");
            subjectField.selectOption("Warranty");

            assertAll(
                    () -> Assertions.assertThat(firstNameField.inputValue()).isEqualTo("Bill"),
                    () -> Assertions.assertThat(lastNameField.inputValue()).isEqualTo("Snow"),
                    () -> Assertions.assertThat(emailNameField.inputValue()).isEqualTo("bill-snow@test.com"),
                    () -> Assertions.assertThat(messageField.inputValue()).isEqualTo("Hello, world!"),
                    () -> Assertions.assertThat(subjectField.inputValue()).isEqualTo("warranty")
            );
        }
    }
}