package com.udacity.jwdnd.course1.cloudstorage;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CredentialPage {
    @FindBy(id = "nav-credentials-tab")
    private WebElement navCredentialTab;

    @FindBy(id = "create-credential-btn")
    private WebElement createCredentialBtn;

    @FindBy(id = "credentialTable")
    private WebElement credentialTable;

    @FindBy(id = "credentialModal")
    private WebElement credentialModal;

    @FindBy(id = "credentialModalLabel")
    private WebElement credentialModalLabel;

    @FindBy(id = "credential-form")
    private WebElement credentialForm;

    @FindBy(id = "credentialId")
    private WebElement credentialIdInput;

    @FindBy(id = "credential-url")
    private WebElement credentialUrlInput;

    @FindBy(id = "credential-username")
    private WebElement credentialUsernameInput;

    @FindBy(id = "credential-password")
    private WebElement credentialPasswordInput;

    @FindBy(id = "credentialSubmit")
    private WebElement credentialSubmitBtn;

    private WebDriver driver;
    private int port;

    public CredentialPage(WebDriver webDriver, int port) {
        PageFactory.initElements(webDriver, this);
        this.driver = webDriver;
        this.port = port;
    }

    public void doCreate(String url, String username, String password) {
        WebDriverWait webDriverWait = new WebDriverWait(this.driver, Duration.ofSeconds(2));

        webDriverWait.until(ExpectedConditions.visibilityOf(this.navCredentialTab));
        this.navCredentialTab.click();

        webDriverWait.until(ExpectedConditions.visibilityOf(this.createCredentialBtn));
        this.createCredentialBtn.click();

        webDriverWait.until(ExpectedConditions.visibilityOf(this.credentialForm));
        webDriverWait.until(ExpectedConditions.visibilityOf(this.credentialModal));
        webDriverWait.until(ExpectedConditions.visibilityOf(this.credentialModalLabel));
        Assertions.assertEquals("Credential", this.credentialModalLabel.getText());

        webDriverWait.until(ExpectedConditions.visibilityOf(this.credentialUrlInput));
        this.credentialUrlInput.click();
        this.credentialUrlInput.sendKeys(url);

        webDriverWait.until(ExpectedConditions.visibilityOf(this.credentialUsernameInput));
        this.credentialUsernameInput.click();
        this.credentialUsernameInput.sendKeys(username);

        webDriverWait.until(ExpectedConditions.visibilityOf(this.credentialPasswordInput));
        this.credentialPasswordInput.click();
        this.credentialPasswordInput.sendKeys(password);

        this.credentialForm.submit();

        webDriverWait.until(ExpectedConditions.invisibilityOf(this.credentialModal));
    }

    public void doUpdate(String url, String username, String password, WebElement credentialElement) {
        WebDriverWait webDriverWait = new WebDriverWait(this.driver, Duration.ofSeconds(2));

        webDriverWait.until(ExpectedConditions.visibilityOf(this.navCredentialTab));
        this.navCredentialTab.click();

        webDriverWait.until(ExpectedConditions.visibilityOf(this.credentialTable));
        WebElement credentialEditBtn = credentialElement.findElement(By.className("btn-edit-credential"));
        credentialEditBtn.click();

        webDriverWait.until(ExpectedConditions.visibilityOf(this.credentialModal));
        webDriverWait.until(ExpectedConditions.visibilityOf(this.credentialModalLabel));
        Assertions.assertEquals("Credential", this.credentialModalLabel.getText());

        webDriverWait.until(ExpectedConditions.visibilityOf(this.credentialUrlInput));
        this.credentialUrlInput.click();
        this.credentialUrlInput.clear();
        this.credentialUrlInput.sendKeys(url);

        webDriverWait.until(ExpectedConditions.visibilityOf(this.credentialUsernameInput));
        this.credentialUsernameInput.click();
        this.credentialUsernameInput.clear();
        this.credentialUsernameInput.sendKeys(username);

        webDriverWait.until(ExpectedConditions.visibilityOf(this.credentialPasswordInput));
        this.credentialPasswordInput.click();
        this.credentialPasswordInput.clear();
        this.credentialPasswordInput.sendKeys(password);

        this.credentialSubmitBtn.submit();

        webDriverWait.until(ExpectedConditions.invisibilityOf(this.credentialModal));
    }

    public void doDelete(WebElement credentialElement) {
        WebDriverWait webDriverWait = new WebDriverWait(this.driver, Duration.ofSeconds(2));

        webDriverWait.until(ExpectedConditions.visibilityOf(this.credentialTable));

        WebElement formElement = credentialElement.findElement(By.tagName("form"));
        WebElement deleteBtn = formElement.findElement(By.tagName("button"));
        deleteBtn.click();

        webDriverWait.until(ExpectedConditions.invisibilityOf(credentialElement));
    }

    public WebElement getNavCredentialTab() {
        return navCredentialTab;
    }

    public WebElement getCreateCredentialBtn() {
        return createCredentialBtn;
    }

    public WebElement getCredentialTable() {
        return credentialTable;
    }

    public WebElement getCredentialModal() {
        return credentialModal;
    }

    public WebElement getCredentialModalLabel() {
        return credentialModalLabel;
    }

    public WebElement getCredentialIdInput() {
        return credentialIdInput;
    }

    public WebElement getCredentialUrlInput() {
        return credentialUrlInput;
    }

    public WebElement getCredentialUsernameInput() {
        return credentialUsernameInput;
    }

    public WebElement getCredentialPasswordInput() {
        return credentialPasswordInput;
    }

    public WebElement getCredentialSubmitBtn() {
        return credentialSubmitBtn;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public int getPort() {
        return port;
    }
}
