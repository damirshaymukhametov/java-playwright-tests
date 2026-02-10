package com.study.playwright.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.nio.file.Path;

public class ContactPage {
    private static final String URL = "https://practicesoftwaretesting.com/contact";

    private final Page page;

    private final Locator firstNameField;
    private final Locator lastNameField;
    private final Locator emailField;
    private final Locator messageField;
    private final Locator subjectSelect;
    private final Locator attachmentField;
    private final Locator sendButton;
    private final Locator successAlert;

    public ContactPage(Page page) {
        this.page = page;
        this.firstNameField = page.getByLabel("First name");
        this.lastNameField = page.getByLabel("Last name");
        this.emailField = page.getByLabel("Email");
        this.messageField = page.getByLabel("Message");
        this.subjectSelect = page.getByLabel("Subject");
        this.attachmentField = page.getByLabel("Attachment");
        this.sendButton = page.getByText("Send");
        this.successAlert = page.locator(".alert-success");
    }

    public ContactPage open() {
        page.navigate(URL);
        return this;
    }

    public ContactPage fillForm(String firstName,
                                String lastName,
                                String email,
                                String message,
                                String subjectValue) {
        firstNameField.fill(firstName);
        lastNameField.fill(lastName);
        emailField.fill(email);
        messageField.fill(message);
        subjectSelect.selectOption(subjectValue);
        return this;
    }

    public ContactPage attachFile(Path file) {
        page.setInputFiles("#attachment", file);
        return this;
    }

    public ContactPage submit() {
        sendButton.click();
        return this;
    }


    public String uploadedFileName() {
        return attachmentField.inputValue();
    }

    public String firstNameValue() {
        return firstNameField.inputValue();
    }

    public String lastNameValue() {
        return lastNameField.inputValue();
    }

    public String emailValue() {
        return emailField.inputValue();
    }

    public String messageValue() {
        return messageField.inputValue();
    }

    public String subjectValue() {
        return subjectSelect.inputValue();
    }

    public boolean isSuccessAlertVisible() {
        return successAlert.isVisible();
    }
}