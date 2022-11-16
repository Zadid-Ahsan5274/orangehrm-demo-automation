package testrunner;

import org.junit.Assert;
import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import setup.Setup;

public class LoginTestRunner extends Setup {

    LoginPage loginPage;
    DashboardPage dashboardPage;

    @Test(priority = 1, description = "Check if login is successful with blank username and blank password")
    public void login_BlankUsername_BlankPassword() throws InterruptedException {
        loginPage = new LoginPage(driver);
        driver.get("https://opensource-demo.orangehrmlive.com/");
        loginPage.doLogin("", "");
        String errorMessage1 = loginPage.errorMessages.get(0).getText();
        String errorMessage2 = loginPage.errorMessages.get(1).getText();
        Assert.assertTrue(errorMessage1.equals("Required"));
        Assert.assertTrue(errorMessage2.equals("Required"));
    }

    @Test(priority = 2, description = "Check if login is successful with blank username and valid password")
    public void login_BlankUsername_validPassword() throws InterruptedException {
        loginPage = new LoginPage(driver);
        driver.get("https://opensource-demo.orangehrmlive.com/");
        loginPage.doLogin("", "admin123");
        String errorMessage1 = loginPage.errorMessages.get(0).getText();
        Assert.assertTrue(errorMessage1.equals("Required"));
    }

    @Test(priority = 3, description = "Check if login is successful with valid username and blank password")
    public void login_ValidUsername_BlankPassword() throws InterruptedException {
        loginPage = new LoginPage(driver);
        driver.get("https://opensource-demo.orangehrmlive.com/");
        loginPage.doLogin("admin", "");
        String errorMessage1 = loginPage.errorMessages.get(0).getText();
        Assert.assertTrue(errorMessage1.equals("Required"));
    }

    @Test(priority = 4, description = "Check if login is successful with valid username and invalid password")
    public void login_ValidUsername_InvalidPassword() throws InterruptedException {
        loginPage = new LoginPage(driver);
        driver.get("https://opensource-demo.orangehrmlive.com/");
        loginPage.doLogin("admin", "admin12345");
        String actualWarningText = loginPage.warningText.getText();
        String expectedWarningText = "Invalid credentials";
        Assert.assertTrue(actualWarningText.equals(expectedWarningText));
    }

    @Test(priority = 5, description = "Check if login is successful with invalid username and valid password")
    public void login_InvalidUsername_ValidPassword() throws InterruptedException {
        loginPage = new LoginPage(driver);
        driver.get("https://opensource-demo.orangehrmlive.com/");
        loginPage.doLogin("adminaa", "admin123");
        String actualWarningText = loginPage.warningText.getText();
        String expectedWarningText = "Invalid credentials";
        Assert.assertTrue(actualWarningText.equals(expectedWarningText));
    }

    @Test(priority = 6, description = "Check if login is successful with invalid username and invalid password")
    public void login_InvalidUsername_InvalidPassword() throws InterruptedException {
        loginPage = new LoginPage(driver);
        driver.get("https://opensource-demo.orangehrmlive.com/");
        loginPage.doLogin("adminaa", "admin12345");
        String actualWarningText = loginPage.warningText.getText();
        String expectedWarningText = "Invalid credentials";
        Assert.assertTrue(actualWarningText.equals(expectedWarningText));
    }

    @Test(priority = 7, description = "Check if login is successful with valid username and valid password")
    public void login_ValidUsername_ValidPassword() throws InterruptedException {
        loginPage = new LoginPage(driver);
        driver.get("https://opensource-demo.orangehrmlive.com/");
        loginPage.doLogin("admin", "admin123");
        Thread.sleep(5000);
        String actualURL = driver.getCurrentUrl();
        String expectedURL = "dashboard";
        Assert.assertTrue(actualURL.contains(expectedURL));
    }

    @Test(priority = 8, description = "Check if user can logout successfully")
    public void doAdminLogout() {
        dashboardPage = new DashboardPage(driver);
        dashboardPage.btnProfileImage.click();
        dashboardPage.linkLogout.click();
    }
}
