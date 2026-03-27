package tests;

import base.BaseTest;
import base.TestBase;
import org.example.BrowserManger.BrowserManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.GridCreation;
import pages.GridWizard;
import pages.LoginPage;

import static com.codeborne.selenide.Selenide.webdriver;
import static java.lang.Thread.sleep;


public class LoginTest extends TestBase {

    GridCreation Grid = new GridCreation();
    GridWizard Wizard = new GridWizard();


    @BeforeMethod
    public void setup() {
        BrowserManager.getDriver();
    }

    @Test
    public void simpleTest() throws InterruptedException {
        System.out.println(webdriver().driver().getWebDriver().getTitle());

        Grid.clickOnAddNewButton();
        Grid.clickOnOptionCardAndValidateNavigation();

        //BrowserManager.navigateToWaveGridWizard();
        Wizard.objectSelection();
        Wizard.salesforceListView();
        Wizard.gridName();
        sleep(3000);
        Wizard.nextButton();
        Wizard.waitForLoaderToDisappear();
        Wizard.clickOnFieldPickerButton();



        Wizard.searchFieldFromFieldPicker(" Account Name ");

    }
}
