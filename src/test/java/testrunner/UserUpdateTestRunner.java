package testrunner;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
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

public class UserUpdateTestRunner extends Setup {
    DashboardPage dashboardPage;

    @BeforeTest
    public void doLogin() throws ParseException, IOException, InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/");
        List data = Utils.readJSONArray("./src/test/resources/Users.json");
        LoginPage loginPage = new LoginPage(driver);
        JSONObject userObj = (JSONObject) data.get(data.size() - 1);
        String username = (String) userObj.get("userName");
        String password = (String) userObj.get("password");
        loginPage.doLogin(username, password);
        Thread.sleep(5000);
    }

    @Test(priority = 1, description = "Check user dashboard")
    public void checkUserDashboard() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        String urlActual = driver.getCurrentUrl();
        String urlExpected = "dashboard";
        Assert.assertTrue(urlActual.contains(urlExpected));
        Thread.sleep(5000);
        driver.findElement(By.partialLinkText("My Info")).click();
    }

    @Test(priority = 2, description = "Upload user image")
    public void uploadImage() throws InterruptedException {
        List<WebElement> images = driver.findElements(By.className("employee-image"));
        images.get(0).click();
        Thread.sleep(10000);
        driver.findElement(By.className("employee-image-action")).sendKeys(".src/test/resources/img_avatar.png");
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("[type=submit]"));
        driver.navigate().back();
    }

    @Test(priority = 3, description = "Update user nationality")
    public void updateUserInfo() throws InterruptedException {
        List<WebElement> headerTitle = driver.findElements(By.className("orangehrm-main-title"));
        Utils.waitForElement(driver, headerTitle.get(0), 50);
        if (headerTitle.get(0).isDisplayed()) {
            EmployeePage employeePage = new EmployeePage(driver);
            employeePage.dropdownBox.get(0).click();
            employeePage.dropdownBox.get(0).sendKeys("b");
            employeePage.dropdownBox.get(0).sendKeys(Keys.ARROW_DOWN);
            employeePage.dropdownBox.get(0).sendKeys(Keys.ARROW_DOWN);
            employeePage.dropdownBox.get(0).sendKeys(Keys.ENTER);
            //Utils.scrollDown(driver);
            List<WebElement> buttons = driver.findElements(By.className("oxd-button"));
            buttons.get(0).click();
            Thread.sleep(5000);
            driver.navigate().refresh();
            Thread.sleep(5000);
            Utils.scrollDown(driver);
            List<WebElement> list = driver.findElements(By.className("oxd-select-text-input"));
            String country = list.get(0).getText();
            System.out.println(country);
            Assert.assertEquals(country, "Bangladeshi");
        }
    }

    @Test(priority = 4, description = "Update user marital status")
    public void updateUserMaritalStatus() throws InterruptedException {
        EmployeePage employeePage = new EmployeePage(driver);
        Utils.scrollDown(driver);
        List<WebElement> marital_status_list = driver.findElements(By.className("oxd-select-text-input"));
        marital_status_list.get(1).sendKeys("S");
        marital_status_list.get(1).sendKeys(Keys.ENTER);
        List<WebElement> buttons = driver.findElements(By.className("oxd-button"));
        buttons.get(0).click();
        Thread.sleep(10000);
        driver.navigate().refresh();
        Thread.sleep(5000);
        //Utils.scrollDown(driver);
        List<WebElement> marital_status_list1 = driver.findElements(By.className("oxd-select-text-input"));
        String marital_status = marital_status_list1.get(1).getText();
        System.out.println(marital_status);
        Assert.assertEquals(marital_status, "Single");
    }

    @AfterTest
    public void doLogout(){
        dashboardPage = new DashboardPage(driver);
        dashboardPage.btnProfileImage.click();
        dashboardPage.linkLogout.click();
    }

}
