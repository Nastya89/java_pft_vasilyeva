package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver driver) {
        super(driver);
    }


    public void selectContact(int index) {
        driver.findElements(By.name("selected[]")).get(index).click();

    }

    public void initContactModification() {
        click(By.cssSelector("img[alt='EDIT']"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("company"), contactData.getCompany());
        type(By.name("home"), contactData.getHome());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
            new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void deleteSelectedContact() {
        click(By.xpath("/html/body/div[1]/div[4]/form[2]/div[2]/input"));
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public void submitContactCreation() {
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='NOTES:'])[1]/following::input[1]")).click();
    }

    public void goToNewContactCreationPage() {
        driver.findElement(By.linkText("ADD_NEW")).click();
    }

    public void createContact(ContactData contact) {
        goToNewContactCreationPage();
        fillContactForm(contact, true);
        submitContactCreation();
        goToHomePage();
    }

    public void goToHomePage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("HOME"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return driver.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {

        List<ContactData> contacts = new ArrayList<>();

        List<WebElement> elements = driver.findElements(By.name("entry"));

        if (elements != null && elements.size() > 0) {
            for (int i = 2; i < elements.size() + 2; i++) {
                String name = driver.findElement(By.xpath("*//tbody/tr[" + i + "]/td[3]")).getText();
                String surname = driver.findElement(By.xpath("*//tbody/tr[" + i + "]/td[2]")).getText();

                ContactData contact = new ContactData(name, null, surname, null, null, null, null, null);
                contacts.add(contact);
            }
        }
        return contacts;
    }
}