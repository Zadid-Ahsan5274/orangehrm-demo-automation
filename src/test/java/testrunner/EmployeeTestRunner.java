package testrunner;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.EmployeePage;
import pages.LoginPage;
import setup.Setup;
import utils.Utils;

public class EmployeeTestRunner extends Setup {

    LoginPage loginPage;
    DashboardPage dashboardPage;
    EmployeePage employeePage;

    @BeforeTest
    public void doAdminLogin() throws InterruptedException {
        loginPage = new LoginPage(driver);
        driver.get("https://opensource-demo.orangehrmlive.com/");
        loginPage.doLogin("admin", "admin123");
        Thread.sleep(5000);
        driver.findElement(By.partialLinkText("PIM")).click();
    }

    @Test(priority = 1, description = "Check if employee can be created with blank Full name and last name")
    public void create_employee_with_blank_fullname_blank_lastname() throws InterruptedException {
        employeePage = new EmployeePage(driver);
        employeePage.btnAddEmployee.get(2).click();
        Thread.sleep(5000);
        employeePage.txtFirstName.sendKeys("");
        employeePage.txtLastName.sendKeys("");
        employeePage.btnSubmit.click();
        String errorMessage1 = employeePage.employeeAddErrorMessages.get(0).getText();
        String errorMessage2 = employeePage.employeeAddErrorMessages.get(1).getText();
        Assert.assertTrue(errorMessage1.equals("Required"));
        Assert.assertTrue(errorMessage2.equals("Required"));
        Thread.sleep(5000);
    }

    @Test(priority = 2, description = "Check if employee creation proceeds with blank or invalid password")
    public void blankOrInvalidPassword() throws InterruptedException {
        employeePage = new EmployeePage(driver);
        // employeePage.btnAddEmployee.get(2).click();
        Thread.sleep(5000);
        employeePage.toggleButton.click();
        employeePage.txtUserCreds.get(5).clear();
        employeePage.txtUserCreds.get(6).clear();
        employeePage.txtUserCreds.get(7).clear();
        employeePage.btnSubmit.click();
        List<WebElement> password_error_messages = driver.findElements(By.className("oxd-input-field-error-message"));
        String error_message = password_error_messages.get(2).getText();
        String error_message1 = password_error_messages.get(3).getText();
        String error_message2 = password_error_messages.get(4).getText();
        Assert.assertTrue(error_message.equals("Required"));
        Assert.assertTrue(error_message1.equals("Required"));
        Assert.assertTrue(error_message2.equals("Required"));
    }

    @Test(priority = 3, description = "Check if can create user with already existing username")
    public void createWithExistingUsername() throws InterruptedException {
        employeePage = new EmployeePage(driver);
        // employeePage.btnAddEmployee.get(2).click();
        Thread.sleep(5000);
        //employeePage.toggleButton.click();
        employeePage.txtUserCreds.get(5).clear();
        employeePage.txtUserCreds.get(6).clear();
        employeePage.txtUserCreds.get(7).clear();
        List<WebElement> password_error_messages = driver.findElements(By.className("oxd-input-field-error-message"));
        employeePage.txtUserCreds.get(5).sendKeys("admin");
        String error_message = password_error_messages.get(2).getText();
        Assert.assertTrue(error_message.contains("exists"));
    }

    @Test(priority = 4, description = "Check if admin can create new employee successfully")
    public void createNewEmployee() throws IOException, ParseException, InterruptedException, UnsupportedFlavorException {
        employeePage = new EmployeePage(driver);
        // employeePage.btnAddEmployee.get(2).click();
        Thread.sleep(5000);
        //employeePage.toggleButton.click();
        List<WebElement> elements = driver.findElements(By.className("oxd-input"));
        elements.get(4).sendKeys(Keys.CONTROL+"a");
        elements.get(4).sendKeys(Keys.CONTROL+"c");
        Utils utils = new Utils();
        utils.generateRandomData();
        String firstName = utils.getFirstname();
        String lastName = utils.getLastname();
        String userName = utils.getUsername();
        String first_employee_id = Utils.pasteValue();
        // int randomId = Utils.generateRandomNumber(1000,9999);
        // String userName = utils.getFirstname()+randomId;
        String password = "P@word123";
        String confirmPassword = password;
        //employeePage.txtUserCreds.get(5).clear();

        employeePage.createEmployee(firstName, lastName, userName, password, confirmPassword);

        Thread.sleep(10000);
        List<WebElement> headerTitle = driver.findElements(By.className("orangehrm-main-title"));
        Assert.assertTrue(headerTitle.get(0).isDisplayed());
        Utils.waitForElement(driver, headerTitle.get(0), 50);
        if (headerTitle.get(0).isDisplayed()) {
            utils.saveJsonList(userName, password);
        }
        Thread.sleep(5000);

        driver.findElement(By.partialLinkText("PIM")).click();
        Thread.sleep(6000);
        employeePage.txtUserCreds.get(1).sendKeys(first_employee_id);
        driver.findElement(By.cssSelector("[type=submit]")).click();
        Thread.sleep(5000);
        String text = employeePage.recordFoundText.get(13).getText();
        Assert.assertTrue(text.contains(" Record Found"));
    }

    @Test(priority = 5, description = "Check if admin can create another new employee successfully")
    public void createAnotherNewEmployee() throws IOException, ParseException, InterruptedException, UnsupportedFlavorException {
        employeePage = new EmployeePage(driver);
        driver.findElement(By.partialLinkText("PIM")).click();
        employeePage.btnAddEmployee.get(2).click();
        List<WebElement> elements = driver.findElements(By.className("oxd-input"));
        elements.get(4).sendKeys(Keys.CONTROL+"a");
        elements.get(4).sendKeys(Keys.CONTROL+"c");
        Thread.sleep(5000);
        employeePage.toggleButton.click();
        Utils utils = new Utils();
        utils.generateRandomData();
        String firstName = utils.getFirstname();
        String lastName = utils.getLastname();
        String userName = utils.getUsername();
        String second_employee_id = Utils.pasteValue();
        // int randomId = Utils.generateRandomNumber(1000,9999);
        // String userName = utils.getFirstname()+randomId;
        String password = "P@word123";
        String confirmPassword = password;
        employeePage.txtUserCreds.get(5).clear();
        employeePage.createEmployee(firstName, lastName, userName, password, confirmPassword);
        String first_employee_id = employeePage.txtUserCreds.get(4).getText();
        Thread.sleep(10000);
        List<WebElement> headerTitle = driver.findElements(By.className("orangehrm-main-title"));
        Assert.assertTrue(headerTitle.get(0).isDisplayed());
        Utils.waitForElement(driver, headerTitle.get(0), 50);
        if (headerTitle.get(0).isDisplayed()) {
            utils.saveJsonList(userName, password);
        }
        Thread.sleep(5000);
        driver.findElement(By.partialLinkText("PIM")).click();
        Thread.sleep(6000);
        employeePage.txtUserCreds.get(1).sendKeys(second_employee_id);
        driver.findElement(By.cssSelector("[type=submit]")).click();
        Thread.sleep(10000);
        String text = employeePage.recordFoundText.get(13).getText();
        Assert.assertTrue(text.contains(" Record Found"));
    }

    @AfterTest
    public void doAdminLogout() {
        dashboardPage = new DashboardPage(driver);
        dashboardPage.btnProfileImage.click();
        dashboardPage.linkLogout.click();
    }

}
