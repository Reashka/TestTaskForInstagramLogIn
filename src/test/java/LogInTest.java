import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogInTest {
    String url = "https://www.instagram.com/";
    String correctEmail = "zhadaniv@mail.ru";
    String incorrectEmail = "zhadan@mail.ru";
    String correctPassword = "nm12kl34op";
    String incorrectPassword = "nm12kl34";
    String requiredCheckElement = "Profile";
    String requiredProfileName = "_zagadochnayadusha_";
    String requiredTextForIncorrectPassword = "Sorry, your password was incorrect. Please double-check your password.";
    Browser browser;

    @BeforeMethod
    void openBrowser() {
        browser = new Browser();
        browser.initWebDriver();
        browser.driver.get(url);}

    @AfterMethod
    void closeBrowser() {
        browser.driver.quit(); }

    //Не стала выносить этот метод в другой класс, так как, по-хорошему, он должен относиться к странице авторизации,
    //а создавать классы для каждой страницы ради 3х тестовых методов я не посчитала нужным.
    void logIn(String login, String password) {
        browser.fillInField("#loginForm div:nth-child(1) input", login);
        browser.fillInField("#loginForm div:nth-child(2) input", password);
        browser.click("#loginForm div:nth-child(3)"); }

    @Test
    public void correctLogIn() {
        logIn(correctEmail, correctPassword);
        browser.click("main > div > div > div > div > div");
        browser.click("button._a9--._ap36._a9_1");
        browser.assertText("div:nth-child(8) span > span", requiredCheckElement);
        browser.click("div:nth-child(8) a > div");
        browser.assertText("div > a > h2", requiredProfileName); }

    @Test
    public void logInWithIncorrectLogin() {
        logIn(incorrectEmail, correctPassword);
        browser.assertText("#loginForm > span > div", requiredTextForIncorrectPassword); }

    @Test
    public void logInWithIncorrectPassword() {
        logIn(correctEmail, incorrectPassword);
        browser.assertText("#loginForm > span > div", requiredTextForIncorrectPassword); }
}
