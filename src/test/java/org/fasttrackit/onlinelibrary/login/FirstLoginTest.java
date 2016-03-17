package org.fasttrackit.onlinelibrary.login;


import com.sdl.selenium.web.utils.Utils;

import org.fasttrackit.exemple.LoginPage;
import org.fasttrackit.util.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class FirstLoginTest extends TestBase {
    private LoginPage loginPage;

    public FirstLoginTest() {
        loginPage = PageFactory.initElements(driver, LoginPage.class);
    }

    @Test
    public void validLoginTest() {
        openLoginPage();
        loginPage.doLogin("eu@fast.com","eu.pass");
        //doLogin("eu@fast.com", "eu.pass");


        try {
            WebElement logoutBtn = driver.findElement(By.linkText("Logout"));
            logoutBtn.click();
        } catch (NoSuchElementException exception) {
            Assert.fail("Could not login.Logout button not found");


        }
    }

    @Test
    public void whenEnterInvalidPasswordIGetErrorMessage() {
        openLoginPage();
        doLogin("eu@fast.com", "wrong.pass");
        loginPage.assertThatErrorIs("Invalid user or password!");
    }

    @Test
    public void whenEnterOnlyEmailIGetErrorMessage() {
        openLoginPage();
        doLogin("eu@fast.com", "");
        loginPage.assertThatErrorIs("Please enter your password!");
    }

    @Test
    public void whenEnterOnlyPasswordIGetErrorMessage() {
        openLoginPage();
        doLogin("", "some_pass");
        loginPage.assertThatErrorIs("Please enter your email!");
    }
    @Test
    public void successChangePassword(){
        openLoginPage();
        doLogin("eu@fast.com", "eu.pass");
               WebElement preferenceButton = driver.findElement(By.xpath("//nav//button"));
        preferenceButton.click();

        try {
            Thread.sleep(1000);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }


         WebElement currentPassField = driver.findElement(By.xpath("//div[@id='preferences-win']//input [@name='password']"));
        currentPassField.sendKeys("eu.pass");

        WebElement newPassField = driver.findElement(By.xpath("//input[@name='newPassword']"));
        newPassField.sendKeys("eu.pass1");

        WebElement repeatPassField = driver.findElement(By.xpath("//input[@name='newPasswordRepeat']"));
        repeatPassField.sendKeys("eu.pass1");

        WebElement saveBtn = driver.findElement(By.cssSelector("#preferences-win button.btn-warning"));
        saveBtn.click();

        WebElement statusElement = driver.findElement(By.cssSelector("#preferences-win .status-msg"));
        System.out.println(statusElement.getText());
        assertThat(statusElement.getText(), is("Your password has been successfully changed."));
    }



    public void doLogin(String userName, String password) {

        loginPage.enterEmail(userName);


        WebElement passField = driver.findElement(By.name("password"));
        passField.sendKeys(password);

        WebElement loginBtn = driver.findElement(By.className("btn"));
        loginBtn.click();
    }

    private void openLoginPage() {
        System.out.println("open login test");
        driver.get("https://rawgit.com/sdl/Testy/master/src/test/functional/app-demo/login.html");
    }


}
