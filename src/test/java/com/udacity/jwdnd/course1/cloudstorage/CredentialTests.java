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
public class CredentialTests extends WebDriverConfig {

    @Test
    public void givenCredentialUrlAndUsernameAndPassword_whenCreatingCredential_thenReturnListOfCredentialContainingNewCredential() {
        SignupPage signupPage = new SignupPage(this.getDriver(), this.getPort());
        signupPage.doMockSignUp("Create Credential", "Create Credential", "CreateCredential", "123");

        LoginPage loginPage = new LoginPage(this.getDriver(), this.getPort());
        loginPage.doLogIn("CreateCredential", "123");

        CredentialPage credentialPage = new CredentialPage(this.getDriver(), this.getPort());
        credentialPage.doCreate("Credential URL", "CredentialUsername", "CredentialPassword");

        List<WebElement> credentialElements = this.getCredentialElements(credentialPage);

        Assertions.assertTrue(!credentialElements.isEmpty());
        Assertions.assertEquals("Credential URL", credentialElements.get(credentialElements.size() - 1)
                .findElement(By.xpath("//th[text()='Credential URL']"))
                .getText());
        Assertions.assertEquals("CredentialUsername", credentialElements.get(credentialElements.size() - 1)
                .findElement(By.xpath("//td[text()='CredentialUsername']"))
                .getText());

        List<WebElement> tdElements = credentialElements.get(credentialElements.size() - 1)
                .findElements(By.tagName("td"));
        WebElement passwordElements = tdElements.get(tdElements.size() - 1);
        Assertions.assertNotEquals("CredentialPassword", passwordElements.getText());
    }

    @Test
    public void givenExistingCredential_whenUpdatingCredential_thenReturnListOfCredentialContainEditedCredential() {
        SignupPage signupPage = new SignupPage(this.getDriver(), this.getPort());
        signupPage.doMockSignUp("Update Credential", "Update Credential", "UpdateCredential", "123");

        LoginPage loginPage = new LoginPage(this.getDriver(), this.getPort());
        loginPage.doLogIn("UpdateCredential", "123");

        CredentialPage credentialPage = new CredentialPage(this.getDriver(), this.getPort());
        credentialPage.doCreate("Credential URL", "CredentialUsername", "CredentialPassword");
        List<WebElement> credentialElements = this.getCredentialElements(credentialPage);
        List<WebElement> tdElements = credentialElements.get(credentialElements.size() - 1)
                .findElements(By.tagName("td"));
        WebElement passwordElements = tdElements.get(tdElements.size() - 1);
        String passwordCreated = passwordElements.getText();

        credentialPage.doUpdate("Credential URL Update", "CredentialUsernameUpdate", "CredentialPasswordUpdate", credentialElements.get(0));
        List<WebElement> updatedCredentialElements = this.getCredentialElements(credentialPage);
        List<WebElement> updatedTdElements = updatedCredentialElements.get(updatedCredentialElements.size() - 1)
                .findElements(By.tagName("td"));
        WebElement updatedPasswordElements = updatedTdElements.get(updatedTdElements.size() - 1);
        String passwordUpdated = updatedPasswordElements.getText();

        Assertions.assertEquals(credentialElements.size(), updatedCredentialElements.size());
        Assertions.assertEquals("Credential URL Update", updatedCredentialElements.get(updatedCredentialElements.size() - 1)
                .findElement(By.xpath("//th[text()='Credential URL Update']"))
                .getText());
        Assertions.assertEquals("CredentialUsernameUpdate", updatedCredentialElements.get(updatedCredentialElements.size() - 1)
                .findElement(By.xpath("//td[text()='CredentialUsernameUpdate']"))
                .getText());
        Assertions.assertNotEquals("CredentialPasswordUpdate", updatedPasswordElements.getText());
        Assertions.assertNotEquals(passwordCreated, passwordUpdated);
    }

    @Test
    public void givenExistingCredential_whenDeletingCredential_thenReturnListOfCredentialNotContainCredentialDeleted() {
        SignupPage signupPage = new SignupPage(this.getDriver(), this.getPort());
        signupPage.doMockSignUp("Delete  Credential", "Delete  Credential", "Delete Credential", "123");

        LoginPage loginPage = new LoginPage(this.getDriver(), this.getPort());
        loginPage.doLogIn("Delete Credential", "123");

        CredentialPage credentialPage = new CredentialPage(this.getDriver(), this.getPort());
        credentialPage.doCreate("Credential URL Delete", "CredentialUsernameDelete", "CredentialPasswordDelete");
        List<WebElement> credentialElements = this.getCredentialElements(credentialPage);
        credentialPage.doDelete(credentialElements.get(0));

        List<WebElement> deletedNoteElements = this.getCredentialElements(credentialPage);

        Assertions.assertNotEquals(credentialElements.size(), deletedNoteElements.size());
        Assertions.assertEquals(List.of(), deletedNoteElements);
    }


    private List<WebElement> getCredentialElements(CredentialPage credentialPage) {
        WebDriverWait webDriverWait = new WebDriverWait(this.getDriver(), Duration.ofSeconds(2));
        WebElement credentialTable = credentialPage.getCredentialTable();
        webDriverWait.until(ExpectedConditions.visibilityOf(credentialTable));
        WebElement credentialTableBody = credentialTable.findElement(By.tagName("tbody"));
        return credentialTableBody.findElements(By.tagName("tr"));
    }
}
