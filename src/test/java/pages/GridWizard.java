package pages;
import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.impl.Waiter;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class GridWizard {

    private final SelenideElement searchObject = $x("//input[@placeholder='Search Objects']");
    private final SelenideElement dropdown = $x("//div[@role='listbox']");
    private final SelenideElement accountOption = $x("//div[@role='listbox']//span[text()='Account']");
    private final SelenideElement listView = $x("//div[@class=\"dropdown-container\"]//li[@class=\"dropdown-item\"][1]");
    private final SelenideElement enterGridName =$x("//input[@label='Global View Name']");
    private final SelenideElement clickOnNextButton=$x("//button[@class=\"next\"]");
    private final SelenideElement fieldPickerButton = $x("//img[@class=\"plus-button\"]");

    private final SelenideElement fieldPickerSearchBox = $x("//input[@data-testid=\"table-fields-search-input\"]");
    private final SelenideElement spinner = $x("//div[contains(@class,'spinner') or contains(@class,'loading')]");
    public void objectSelection(){

        // Step 1: Click input → opens dropdown
        searchObject.shouldBe(visible)
                .scrollTo()
                .click();

        // Step 2: Wait dropdown visible
        dropdown.shouldBe(visible);

        // Step 3: Type Account
        actions().sendKeys("Account").perform();

        // Step 4: Wait option and click
        accountOption.shouldBe(visible)
                .click();
    }
    public void salesforceListView(){
        listView.shouldBe(visible)
                .shouldBe()
                .click();

    }
    public void gridName() {

        sleep(2000);
        enterGridName.shouldBe(visible).click(ClickOptions.usingJavaScript()).clear();
        enterGridName.setValue("Automation Test Grid");
    }
    public void nextButton(){
        clickOnNextButton.shouldBe(visible)
                .shouldBe(clickable).click(ClickOptions.usingJavaScript())
                .click();

    }
    public void waitForLoaderToDisappear() {

        SelenideElement loader = $x("//div[@id='component-spinner']");

        // If loader appears → wait till it disappears
        if (loader.exists()) {
            loader.shouldBe(visible, Duration.ofSeconds(10)); // optional
            loader.should(disappear, Duration.ofSeconds(30));
        }
    }
    public void clickOnFieldPickerButton(){
        fieldPickerButton.shouldBe(visible)
                .shouldBe(clickable)
                .click();
    }

    public void searchFieldFromFieldPicker(String fieldName){
        Wait().until(webDriver ->
                executeJavaScript("return document.readyState").equals("complete")
        );
        fieldPickerSearchBox.shouldBe(visible)
                .click();
        fieldPickerSearchBox.setValue(fieldName);
        SelenideElement fieldCheckbox = $x(
                "//span[text()='" + fieldName + "']/ancestor::div[contains(@class,'field-picker-list-item')]//input[@type='checkbox']"
        );

        fieldCheckbox.shouldBe(visible)
                .click();

    }

}
