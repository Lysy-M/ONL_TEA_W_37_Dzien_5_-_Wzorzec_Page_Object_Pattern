package pl.coderslab.hotel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HotelAuthPage {

    private final WebDriver driver;
    @FindBy(id = "email_create")
    private WebElement newUserEmailInput;

    @FindBy(id = "SubmitCreate")
    private WebElement createNewAccountBtn;

    @FindBy(name = "email")
    private WebElement loginEmailInput;

    @FindBy(name = "passwd")
    private WebElement loginPasswdInput;

    @FindBy(id = "SubmitLogin")
    private WebElement loginBtn;

    public HotelAuthPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HotelRegisterPage enterNewUserEmailAndSubmit(String email) {
        newUserEmailInput.clear();
        newUserEmailInput.sendKeys(email);

        createNewAccountBtn.click();
        return new HotelRegisterPage(driver);
    }

    public HotelMyAccountPage loginAs(String email, String passwd) {
        loginEmailInput.clear();
        loginEmailInput.sendKeys(email);

        loginPasswdInput.clear();
        loginPasswdInput.sendKeys(passwd);

        loginBtn.click();
        return new HotelMyAccountPage(driver);
    }
}
