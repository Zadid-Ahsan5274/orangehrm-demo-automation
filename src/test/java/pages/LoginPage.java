package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(name = "username")
    WebElement txtUserName;

    @FindBy(name = "password")
    WebElement txtPassword;

    @FindBy(className = "oxd-input-field-error-message")
    public List<WebElement> errorMessages;

    @FindBy(className = "oxd-alert-content-text")
    public WebElement warningText;

    @FindBy(css = "[type=submit]")
    WebElement btnSubmit;

    @FindBy(className = "oxd-userdropdown-img")
    WebElement imgProfile;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void doLogin(String username, String password) {
        txtUserName.sendKeys(username);
        txtPassword.sendKeys(password);
        btnSubmit.click();
    }
}
