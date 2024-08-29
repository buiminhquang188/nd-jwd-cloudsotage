package com.udacity.jwdnd.course1.cloudstorage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginPageTests extends WebDriverConfig {
    @Test
    public void givenNoAuthentication_whenAccessToHomePage_thenRedirectToLoginPage() {
        this.getDriver()
                .get("http://localhost:" + this.getPort() + "/home");
        Assertions.assertEquals("http://localhost:" + this.getPort() + "/login", this.getDriver()
                .getCurrentUrl());
    }

    @Test
    public void givenNoAuthentication_whenAccessToLoginPage_thenReturnLoginPage() {
        this.getDriver()
                .get("http://localhost:" + this.getPort() + "/login");
        Assertions.assertEquals("http://localhost:" + this.getPort() + "/login", this.getDriver()
                .getCurrentUrl());
    }

    @Test
    public void givenNoAuthentication_whenAccessToSignupPage_thenReturnSignupPage() {
        this.getDriver()
                .get("http://localhost:" + this.getPort() + "/signup");
        Assertions.assertEquals("http://localhost:" + this.getPort() + "/signup", this.getDriver()
                .getCurrentUrl());
    }

    @Test
    public void givenUserSignupAndLogin_whenLogout_thenRedirectToLoginPage() {
        SignupPage signupPage = new SignupPage(this.getDriver(), this.getPort());
        signupPage.doMockSignUp("Test", "Test", "Test", "123");

        LoginPage loginPage = new LoginPage(this.getDriver(), this.getPort());
        loginPage.doLogIn("Test", "123");

        WebDriverWait webDriverWait = new WebDriverWait(this.getDriver(), Duration.ofSeconds(2));
        webDriverWait.until(ExpectedConditions.titleContains("Home"));

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logoutDiv")));
        WebElement wrapperLogoutDiv = this.getDriver()
                .findElement(By.id("logoutDiv"));
        WebElement formDiv = wrapperLogoutDiv.findElement(By.tagName("form"));
        formDiv.submit();

        webDriverWait.until(ExpectedConditions.titleContains("Login"));
        Assertions.assertEquals("http://localhost:" + this.getPort() + "/login", this.getDriver()
                .getCurrentUrl());
    }
}
