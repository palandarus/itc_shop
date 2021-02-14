package ru.geekbrains.steps;


import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.geekbrains.DriverInitializer;

import static org.assertj.core.api.Assertions.assertThat;

public class BrandSteps {

    private WebDriver webDriver = null;

    @Given("^I open web browser$")
    public void iOpenFirefoxBrowser() throws Throwable {
        webDriver = DriverInitializer.getDriver();
    }

    @When("^I navigate to login page$")
    public void iNavigateToLoginHtmlPage() throws Throwable {
        webDriver.get(DriverInitializer.getProperty("login.url"));
    }

    @When("^I click on login button$")
    public void iClickOnLoginButton() throws Throwable {
        WebElement webElement = webDriver.findElement(By.id("btn-login"));
        webElement.click();
    }

    @When("^I provide username as \"([^\"]*)\" and password as \"([^\"]*)\"$")
    public void iProvideUsernameAsAndPasswordAs(String username, String password) throws Throwable {
        WebElement webElement = webDriver.findElement(By.id("username"));
        webElement.sendKeys(username);
        Thread.sleep(2000);
        webElement = webDriver.findElement(By.id("password"));
        webElement.sendKeys(password);
        Thread.sleep(2000);
    }

    @When("^I navigate to brand page$")
    public void iNavigateToBrandsHtmlPage() throws Throwable {
        webDriver.get(DriverInitializer.getProperty("brands.url"));
    }

    @When("^I click on create new button$")
    public void iClickOnCreateNewBrandButton() throws Throwable {
        WebElement webElement = webDriver.findElement(By.id("createNewBrandButton"));
        webElement.click();
    }

    @When("^I provide brandname as \"([^\"]*)\"$")
    public void iProvideBrandNameAs(String brandName) throws Throwable {
        WebElement webElement = webDriver.findElement(By.id("name"));
        webElement.sendKeys(brandName);
        Thread.sleep(2000);
    }

    @When("^I click on create new button$")
    public void iClickOnSubmitButton() throws Throwable {
        WebElement webElement = webDriver.findElement(By.id("submitButton"));
        webElement.click();
    }













    @When("^Open dropdown menu$")
    public void openDropDownMenu() throws InterruptedException {
        WebElement webElement = webDriver.findElement(By.id("dd-user"));
        Thread.sleep(1000);
        webElement.click();
        Thread.sleep(10000);
    }

    @When("^click logout button$")
    public void clickLogoutButton() {
        WebElement webElement = webDriver.findElement(By.id("link-logout"));
        webElement.click();
    }

    @Then("^user logged out$")
    public void userLoggedOut() {
        webDriver.findElement(By.id("username"));
        webDriver.findElement(By.id("password"));
    }

    @After
    public void quitBrowser() {
        webDriver.quit();
    }

}
