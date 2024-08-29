package com.udacity.jwdnd.course1.cloudstorage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"server.port=8080"})
public class NoteTests extends WebDriverConfig {
    @Test
    public void givenNoteTitleAndNoteDescription_whenCreatingNote_thenReturnNewNoteInTable() {
        SignupPage signupPage = new SignupPage(this.getDriver(), this.getPort());
        signupPage.doMockSignUp("Create Note", "Create Note", "createnote", "123");

        LoginPage loginPage = new LoginPage(this.getDriver(), this.getPort());
        loginPage.doLogIn("createnote", "123");

        NotePage notePage = new NotePage(this.getPort(), this.getDriver());
        notePage.doCreate("Note Title", "Note Description");
        List<WebElement> createdNoteElements = this.getNotesElement(notePage);

        Assertions.assertEquals(true, !createdNoteElements.isEmpty());
        Assertions.assertEquals("Note Title", createdNoteElements.get(createdNoteElements.size() - 1)
                .findElement(By.xpath("//th[text()='Note Title']"))
                .getText());
        Assertions.assertEquals("Note Description", createdNoteElements.get(createdNoteElements.size() - 1)
                .findElement(By.xpath("//td[text()='Note Description']"))
                .getText());
    }

    @Test
    public void givenExistingNote_whenEditingNote_thenReturnUpdatedNoteInTable() {
        SignupPage signupPage = new SignupPage(this.getDriver(), this.getPort());
        signupPage.doMockSignUp("Edit Note", "Edit Note", "editnote", "123");

        LoginPage loginPage = new LoginPage(this.getDriver(), this.getPort());
        loginPage.doLogIn("editnote", "123");

        NotePage notePage = new NotePage(this.getPort(), this.getDriver());
        notePage.doCreate("Note Title", "Note Description");
        List<WebElement> noteElements = this.getNotesElement(notePage);

        notePage.doEdit("Note Title Edit", "Note Description Edit", noteElements.get(0));
        List<WebElement> editedNoteElements = this.getNotesElement(notePage);

        Assertions.assertEquals(noteElements.size(), editedNoteElements.size());
        Assertions.assertEquals("Note Title Edit", editedNoteElements.get(0)
                .findElement(By.xpath("//th[text()='Note Title Edit']"))
                .getText());
        Assertions.assertEquals("Note Description Edit", editedNoteElements.get(0)
                .findElement(By.xpath("//td[text()='Note Description Edit']"))
                .getText());
    }

    @Test
    public void givenExistingNote_whenDeletingNote_thenReturnRemainingNoteInTable() {
        SignupPage signupPage = new SignupPage(this.getDriver(), this.getPort());
        signupPage.doMockSignUp("Delete Note", "Delete Note", "deletenote", "123");

        LoginPage loginPage = new LoginPage(this.getDriver(), this.getPort());
        loginPage.doLogIn("deletenote", "123");

        NotePage notePage = new NotePage(this.getPort(), this.getDriver());
        notePage.doCreate("Note Title", "Note Description");
        List<WebElement> noteElements = this.getNotesElement(notePage);

        notePage.doDelete(noteElements.get(0));
        List<WebElement> deletedNoteElements = this.getNotesElement(notePage);

        Assertions.assertNotEquals(noteElements.size(), deletedNoteElements.size());
        Assertions.assertEquals(List.of(), deletedNoteElements);
    }

    private List<WebElement> getNotesElement(NotePage notePage) {
        WebDriverWait webDriverWait = new WebDriverWait(this.getDriver(), Duration.ofSeconds(2));
        WebElement userTable = notePage.getUserTable();
        webDriverWait.until(ExpectedConditions.visibilityOf(userTable));
        WebElement tableBody = userTable.findElement(By.tagName("tbody"));
        return tableBody.findElements(By.tagName("tr"));
    }
}
