package com.study.playwright.tests;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import com.study.playwright.config.HeadlessChromeOptions;
import com.study.playwright.pages.MainPage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@UsePlaywright(HeadlessChromeOptions.class)
class AddItemsToTheCartTest {

    @Test
    @DisplayName("Search for bolts")
    void searchForBolts(Page page) {
        MainPage mainPage = new MainPage(page).open()
                .searchFor("Bolt");

        var products = mainPage.getProductNames();
        int cardsCount = mainPage.getResultsCount();

        Assertions.assertThat(products.get(0)).contains("Bolt");
        Assertions.assertThat(cardsCount).isEqualTo(2);
    }
}