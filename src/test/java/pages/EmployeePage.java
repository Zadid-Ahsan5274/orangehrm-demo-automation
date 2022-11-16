package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EmployeePage {

    @FindBy(className = "oxd-button")
    public List<WebElement> btnAddEmployee;

    @FindBy(css = "[type=submit]")
    public WebElement btnSubmit;

    @FindBy(name = "firstName")
    public WebElement txtFirstName;

    @FindBy(name = "lastName")
    public WebElement txtLastName;

    @FindBy(className = "oxd-switch-input")
    public WebElement toggleButton;

    @FindBy(className = "oxd-input")
    public List<WebElement> txtUserCreds;

    @FindBy(className = "oxd-select-text-input")
    public List<WebElement> dropdownBox;

    @FindBy(className = "oxd-input-field-error-message")
    public WebElement lblValidationError;

    @FindBy(className = "oxd-text")
    public List<WebElement> recordFoundText;

    @FindBy(className = "oxd-input-field-error-message")
    public List<WebElement> employeeAddErrorMessages;

    public EmployeePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void createEmployee(String firstName, String lastName, String userName, String password,
                               String confirmPassword) throws InterruptedException {
        Thread.sleep(2000);
        txtFirstName.sendKeys(firstName);
        txtLastName.sendKeys(lastName);
        txtUserCreds.get(5).sendKeys(userName); // username
        txtUserCreds.get(6).sendKeys(password); // password
        txtUserCreds.get(7).sendKeys(confirmPassword); // confirm password
        btnSubmit.click();
    }

}
