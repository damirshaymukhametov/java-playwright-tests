package com.study.playwright;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@UsePlaywright(HeadlessChromeOptions.class)
public class AddItemsToTheCartTest {
    @DisplayName("Search for bolts")
    @Test
    void searchForBolts(Page page) {

      //  page.onConsoleMessage(msg -> System.out.println(msg.txt()));

        page.navigate("https://practicesoftwaretesting.com/");
        page.getByPlaceholder("Search").fill("Bolt");
        page.getByPlaceholder("Search").press("Enter");

        page.waitForLoadState();
        page.waitForCondition(() -> page.getByTestId("product-name").count()>1);

        List<String> products = page.getByTestId("product-name").allTextContents();
        Assertions.assertThat(products.get(0).contains("Bolt"));

        assertThat(page.locator(".card")).hasCount(2);
    }
}
