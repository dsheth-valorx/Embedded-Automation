package base;
import com.codeborne.selenide.Configuration;
import org.example.BrowserManger.BrowserManager;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    @BeforeMethod
    public void setup() {
        BrowserManager.getDriver();
    }
}
