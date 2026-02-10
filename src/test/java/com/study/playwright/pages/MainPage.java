package com.study.playwright.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;

public class MainPage {
    private static final String BASE_URL = "https://practicesoftwaretesting.com";

    private final Page page;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator productCards;
    private final Locator productNames;
    private final Locator sortSelect;
    private final Locator productPrices;

    public MainPage(Page page) {
        this.page = page;
        this.searchInput = page.getByPlaceholder("Search");
        this.searchButton = page.locator("button:has-text('Search')");
        this.productCards = page.locator(".card");
        this.productNames = page.getByTestId("product-name");
        this.sortSelect = page.getByTestId("sort");
        this.productPrices = page.getByTestId("product-price");
    }

    public MainPage open() {
        page.navigate(BASE_URL);
        return this;
    }

    public MainPage searchFor(String text) {
        searchInput.fill(text);
        searchButton.click();
        page.waitForLoadState();
        page.waitForCondition(() -> productNames.count() > 0);
        return this;
    }

    public int getResultsCount() {
        return productCards.count();
    }

    public List<String> getProductNames() {
        return productNames.allTextContents();
    }

    public String getTitle() {
        return page.title();
    }

    public MainPage sortByPriceHighToLow() {
        page.waitForResponse("**/products?page=0&sort**",
                () -> sortSelect.selectOption("Price (High - Low)")
        );
        return this;
    }

    public List<Double> getProductPrices() {
        return productPrices.allInnerTexts().stream()
                .map(MainPage::extractPrice)
                .toList();
    }

    private static double extractPrice(String price) {
        return Double.parseDouble(price.replace("$", ""));
    }
}