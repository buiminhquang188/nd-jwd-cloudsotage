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

public class NotePage {
    @FindBy(id = "nav-notes-tab")
    private WebElement navNotesTab;

    @FindBy(id = "create-note-btn")
    private WebElement createNoteBtn;

    @FindBy(id = "noteModal")
    private WebElement noteModal;

    @FindBy(id = "noteModalLabel")
    private WebElement noteModalLabel;

    @FindBy(id = "note-title")
    private WebElement noteTitleInput;

    @FindBy(id = "note-description")
    private WebElement noteDescriptionInput;

    @FindBy(id = "btn-note-submit")
    private WebElement submitNoteBtn;

    @FindBy(id = "userTable")
    private WebElement userTable;

    private int port;
    private WebDriver driver;

    public NotePage(int port, WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.port = port;
        this.driver = driver;
    }

    public void doCreate(String noteTitle, String noteDescription) {
        WebDriverWait webDriverWait = new WebDriverWait(this.driver, Duration.ofSeconds(2));

        webDriverWait.until(ExpectedConditions.visibilityOf(this.navNotesTab));
        this.navNotesTab.click();

        webDriverWait.until(ExpectedConditions.visibilityOf(this.createNoteBtn));
        this.createNoteBtn.click();

        webDriverWait.until(ExpectedConditions.visibilityOf(this.noteModalLabel));
        Assertions.assertEquals("Note", this.noteModalLabel.getText());

        webDriverWait.until(ExpectedConditions.visibilityOf(this.noteTitleInput));
        this.noteTitleInput.click();
        this.noteTitleInput.sendKeys(noteTitle);

        webDriverWait.until(ExpectedConditions.visibilityOf(this.noteDescriptionInput));
        this.noteDescriptionInput.click();
        this.noteDescriptionInput.sendKeys(noteDescription);

        webDriverWait.until(ExpectedConditions.visibilityOf(this.submitNoteBtn));
        this.submitNoteBtn.click();

        webDriverWait.until(ExpectedConditions.invisibilityOf(this.noteModal));
    }

    public void doEdit(String noteTitle, String noteDescription, WebElement noteElement) {
        WebDriverWait webDriverWait = new WebDriverWait(this.driver, Duration.ofSeconds(2));

        WebElement editBtn = noteElement.findElement(By.className("edit-button"));
        webDriverWait.until(ExpectedConditions.visibilityOf(editBtn));
        editBtn.click();

        webDriverWait.until(ExpectedConditions.visibilityOf(this.noteModalLabel));
        Assertions.assertEquals("Note", this.noteModalLabel.getText());

        webDriverWait.until(ExpectedConditions.visibilityOf(this.noteTitleInput));
        this.noteTitleInput.click();
        this.noteTitleInput.clear();
        this.noteTitleInput.sendKeys(noteTitle);

        webDriverWait.until(ExpectedConditions.visibilityOf(this.noteDescriptionInput));
        this.noteDescriptionInput.click();
        this.noteDescriptionInput.clear();
        this.noteDescriptionInput.sendKeys(noteDescription);

        webDriverWait.until(ExpectedConditions.visibilityOf(this.submitNoteBtn));
        this.submitNoteBtn.click();

        webDriverWait.until(ExpectedConditions.invisibilityOf(this.noteModal));
    }

    public void doDelete(WebElement noteElement) {
        WebDriverWait webDriverWait = new WebDriverWait(this.driver, Duration.ofSeconds(2));

        WebElement formElement = noteElement.findElement(By.tagName("form"));
        webDriverWait.until(ExpectedConditions.visibilityOf(formElement));

        WebElement deleteBtn = formElement.findElement(By.tagName("button"));
        webDriverWait.until(ExpectedConditions.visibilityOf(deleteBtn));

        deleteBtn.click();
        webDriverWait.until(ExpectedConditions.invisibilityOf(noteElement));
    }

    public WebElement getNavNotesTab() {
        return navNotesTab;
    }

    public WebElement getCreateNoteBtn() {
        return createNoteBtn;
    }

    public WebElement getNoteModal() {
        return noteModal;
    }

    public WebElement getNoteTitleInput() {
        return noteTitleInput;
    }

    public WebElement getNoteDescriptionInput() {
        return noteDescriptionInput;
    }

    public WebElement getSubmitNoteBtn() {
        return submitNoteBtn;
    }

    public WebElement getUserTable() {
        return userTable;
    }

    public void setUserTable(WebElement userTable) {
        this.userTable = userTable;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}
