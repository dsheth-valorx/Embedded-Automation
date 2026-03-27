package org.example.BrowserManger;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BrowserManager {

    private static WebDriver driver;

    public static WebDriver getDriver() {

        if (WebDriverRunner.hasWebDriverStarted()) {
            return WebDriverRunner.getWebDriver();
        }

        System.setProperty("webdriver.chrome.silentOutput", "true");
        Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--start-maximized");

        // attach to running chrome
        options.setExperimentalOption("debuggerAddress", "localhost:9222");
        options.setExperimentalOption("enableExtensionTargets", true);

        driver = new ChromeDriver(options);

        WebDriverRunner.setWebDriver(driver);

        navigateToWaveEmbeddedDashboard();

        return driver;
    }

    private static void navigateToWaveEmbeddedDashboard() {

        String currentUrl = Selenide.webdriver().driver().url();

        if (!currentUrl.contains("valorxwave__Dashboard")) {

            String host = currentUrl.split("/lightning")[0];
            String waveDashboardUrl = host + "/lightning/n/valorxwave__Dashboard";

            System.out.println("Navigating to Wave Embedded Dashboard: " + waveDashboardUrl);

            Selenide.open(waveDashboardUrl);
        }
    }

    public static void navigateToWaveGridWizard() {
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);
        if (!currentUrl.contains("Grid_wizard")) {
            String host;
            if (currentUrl.contains("/lightning")) {
                host = currentUrl.split("/lightning")[0];
            } else {
                host = currentUrl;
            }
            String gridWizardUrl = host + "/lightning/n/Grid_wizard";
            System.out.println("Navigating to Grid Wizard: " + gridWizardUrl);
            Selenide.open(gridWizardUrl);
        }
    }

    public static void closeBrowser() {

        if (WebDriverRunner.hasWebDriverStarted()) {
            WebDriverRunner.getWebDriver().quit();
        }
    }
}