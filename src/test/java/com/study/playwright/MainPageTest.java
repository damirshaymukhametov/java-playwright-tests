package com.study.playwright;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@UsePlaywright(HeadlessChromeOptions.class)
public class MainPageTest {

    @Test
    void shouldShowTheTitlePage(Page page) {
        page.navigate("https://practicesoftwaretesting.com/");
        String title = page.title();

        Assertions.assertTrue(title.contains("Practice Software Testing"));
    }

    @Test
    void shouldBeAbleToSearch(Page page) {
        page.navigate("https://practicesoftwaretesting.com/");
        page.locator("#search-query").fill("Bolt");
        page.locator("button:has-text('Search')").click();

        int searchingResults = page.locator(".card").count();

        Assertions.assertTrue(searchingResults > 1);
    }
}
