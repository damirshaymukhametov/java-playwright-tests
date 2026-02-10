package com.study.playwright.tests;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import com.study.playwright.config.HeadlessChromeOptions;
import com.study.playwright.pages.MainPage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@UsePlaywright(HeadlessChromeOptions.class)
public class MainPageTest {

    @Test
    @DisplayName("Main page — title and search test")
    void shouldShowTitleAndSearch(Page page) {
        MainPage mainPage = new MainPage(page).open();

        Assertions.assertThat(mainPage.getTitle())
                .contains("Practice Software Testing");

        mainPage.searchFor("Bolt");
        int results = mainPage.getResultsCount();

        Assertions.assertThat(results).isGreaterThan(1);
    }
}