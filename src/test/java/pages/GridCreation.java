package pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

import java.time.Duration;

import java.util.List;

public class GridCreation {


    private final SelenideElement addNewButton = $x("//span[text()='Add New']/ancestor::button");
    private final SelenideElement popupBox = $x("//div[contains(@class,'popup-box')]");

    private final SelenideElement optionCard = $x("//div[@class='card-area']//button[contains(@class,'option-card')]");

    public void clickOnAddNewButton() {

        addNewButton.shouldBe(visible, Duration.ofSeconds(20))
                .shouldBe(enabled)
                .scrollTo()
                .click();

        popupBox.shouldBe(visible, Duration.ofSeconds(10));
    }

    public void clickOnOptionCardAndValidateNavigation() {

        optionCard.shouldBe(visible)
                .shouldBe(enabled)
                .click();
        // ✅ Wait for URL change
        webdriver().shouldHave(urlContaining("Grid_Wizard"), Duration.ofSeconds(15));

        // ✅ Wait for page load complete (no loader selector)
        Wait().until(webDriver ->
                executeJavaScript("return document.readyState").equals("complete")
        );

        System.out.println("Page fully loaded");
    }
}
