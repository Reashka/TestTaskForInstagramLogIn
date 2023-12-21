import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Browser {
    RemoteWebDriver driver;
    ChromeOptions setUpOption() {
        ChromeOptions options = new ChromeOptions();
        options.setCapability("browserVersion", "119.0");
        options.setCapability("selenoid:options", new HashMap<String, Object>() {{
            put("name", "Test badge..."); //How to add test badge
            put("sessionTimeout", "15m"); //How to set session timeout
            put("env", new ArrayList<String>() {{ add("TZ=UTC"); }}); //How to set timezone
            put("labels", new HashMap<String, Object>() {{ put("manual", "true"); }}); //How to add "trash" button
            put("enableVideo", true); }}); //How to enable video recording
        return options; }

    public void initWebDriver() {
        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), setUpOption()); }
        catch (MalformedURLException e) {
            System.out.println(e.getMessage()); }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); }

    public void click(String cssSelector) {
        WebElement searchButtonSaveLoginInfo = driver.findElement(By.cssSelector(cssSelector));
        searchButtonSaveLoginInfo.click(); }

    public void fillInField(String cssSelector, String text) {
        WebElement searchField = driver.findElement(By.cssSelector(cssSelector));
        searchField.sendKeys(text); }

    public void assertText(String cssSelector, String requiredText) {
        WebElement searchPasswordIsIncorrect = driver.findElement(By.cssSelector(cssSelector));
        String textForIncorrectPassword = searchPasswordIsIncorrect.getText();
        Assertions.assertEquals(requiredText, textForIncorrectPassword); }
}
