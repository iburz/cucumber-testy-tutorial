package org.fasttrackit.onlinelibrary.login;


import org.fasttrackit.exemple.ChangePasswordPage;
import org.fasttrackit.exemple.LoginPage;
import org.fasttrackit.exemple.NavigationPage;
import org.fasttrackit.util.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ChangePasswordTest extends TestBase {
    private LoginPage loginPage;
    private ChangePasswordPage changePasswordPage;
    private NavigationPage navigationPage;

    public ChangePasswordTest() {
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        changePasswordPage = PageFactory.initElements(driver, ChangePasswordPage.class);
        navigationPage= PageFactory.initElements(driver, NavigationPage.class);
    }


    @Test
    public void successChangePassword(){
        openLoginPage();
        doLogin("eu@fast.com", "eu.pass");

            navigationPage.navigationPage();

        changePasswordPage.changePassword("eu.pass","eu.pass1");

        String statusElementText=  changePasswordPage.getStatusMessage();

        System.out.println(statusElementText);
        assertThat(statusElementText, is("Your password has been successfully changed."));
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
