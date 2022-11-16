package testrunner;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.EmployeePage;
import pages.LoginPage;
import setup.Setup;

public class DashboardTestRunner extends Setup {

    LoginPage loginPage;
    DashboardPage dashboardPage;

    @BeforeTest
    public void doAdminLogin() throws InterruptedException {
        loginPage = new LoginPage(driver);
        driver.get("https://opensource-demo.orangehrmlive.com/");
        loginPage.doLogin("admin", "admin123");
        Thread.sleep(5000);
    }

    @Test(priority = 1, description = "Check dashboard URL")
    public void checkDashboardURL(){
        dashboardPage = new DashboardPage(driver);
        String actual_url = driver.getCurrentUrl();
        String expected_url = "dashboard";
        Assert.assertTrue(actual_url.contains(expected_url));
    }

    @Test(priority = 2, description = "Check if profile image exists")
    public void checkProfileImage(){
        dashboardPage = new DashboardPage(driver);
        Boolean isImageDisplayed = dashboardPage.btnProfileImage.isDisplayed();
        Assert.assertTrue(isImageDisplayed);
    }

    @AfterTest
    public void doAdminLogout() {
        dashboardPage = new DashboardPage(driver);
        dashboardPage.btnProfileImage.click();
        dashboardPage.linkLogout.click();
    }

}
