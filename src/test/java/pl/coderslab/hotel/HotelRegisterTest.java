package pl.coderslab.hotel;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class HotelRegisterTest {

    private static WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://hotel-testlab.coderslab.pl/en/");
    }

    @Test
    public void testNewUserRegistration() {
        HotelMainPage mainPage = new HotelMainPage(driver);
//        mainPage.signIn();
//        HotelAuthPage authPage = new HotelAuthPage(driver);
        HotelAuthPage authPage = mainPage.signInWithPage();
        String generatedEmail = generateUniqueEmail();
        String enteredUserName = "John";
        HotelRegisterPage registerPage = authPage.enterNewUserEmailAndSubmit(generatedEmail);
        registerPage.provideRequiredUserData(enteredUserName, "Doe", "secretPass");
        Assertions.assertEquals(generatedEmail, registerPage.getEmail());
        System.out.println("Generated email: " + generatedEmail);

        HotelMyAccountPage myAccountPage = registerPage.register();
        Assertions.assertTrue(myAccountPage.isSuccessAlertDisplayed());
        Assertions.assertEquals("Your account has been created.", myAccountPage.getSuccessMessage());
        Assertions.assertEquals(enteredUserName, myAccountPage.getUserName());
    }

    private String generateUniqueEmail() {
        return "artur" + System.currentTimeMillis() + "@test.com";
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
