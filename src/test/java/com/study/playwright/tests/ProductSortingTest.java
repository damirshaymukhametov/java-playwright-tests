package com.study.playwright.tests;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import com.study.playwright.config.HeadlessChromeOptions;
import com.study.playwright.pages.MainPage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

@UsePlaywright(HeadlessChromeOptions.class)
class ProductSortingTest {

    @Test
    @DisplayName("Sort by price on main page test")
    void sortByDescendingPrice(Page page) {
        MainPage mainPage = new MainPage(page)
                .open()
                .sortByPriceHighToLow();

        List<Double> productPrices = mainPage.getProductPrices();

        Assertions.assertThat(productPrices)
                .isNotEmpty()
                .isSortedAccordingTo(Comparator.reverseOrder());
    }
}