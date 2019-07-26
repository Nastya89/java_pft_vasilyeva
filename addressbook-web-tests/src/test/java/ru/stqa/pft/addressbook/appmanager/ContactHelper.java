package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver driver) {
        super(driver);
    }


    public void selectContact(int index) {
        driver.findElements(By.name("selected[]")).get(index).click();

    }

    public void selectContactByName(ContactData contact) {
        driver.findElement(By.xpath("*//input[contains(@title,'"+ contact.getFirstname()+ " " + contact.getLastname()+ "')]")).click();

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
            ContactData withGroup = contactData.withGroup("Test1");
            new Select(driver.findElement(By.name("new_group"))).selectByVisibleText((withGroup.getGroup()));
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

    public void create(ContactData contact) {
        goToNewContactCreationPage();
        fillContactForm(contact, true);
        submitContactCreation();
        contactCache=null;
        goToHomePage();
    }

    public void modify(ContactData contactToModify, ContactData dataForModification) {
        selectContactByName(contactToModify);
        initContactModification();
        fillContactForm(dataForModification
                ,false);
        submitContactModification();
        contactCache=null;
        goToHomePage();
    }

    public void delete(int lastElementIndex) {
        selectContact(lastElementIndex);
        deleteSelectedContact();
        acceptAlert();
        goToHomePage();
    }
    public void deleteContact(ContactData contact) {
        selectContactByName(contact);
        deleteSelectedContact();
        acceptAlert();
        contactCache=null;
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

    public int count() {
        return driver.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<>();
        //Contacts contacts = new Contacts();

        List<WebElement> elements = driver.findElements(By.name("entry"));

        if (elements != null && elements.size() > 0) {
            for (int i = 2; i < elements.size() + 2; i++) {
                String name = driver.findElement(By.xpath("*//tbody/tr[" + i + "]/td[3]")).getText();
                String surname = driver.findElement(By.xpath("*//tbody/tr[" + i + "]/td[2]")).getText();

                contacts.add(new ContactData().withFirstname(name).withLastname(surname));
            }
        }
        return contacts;
    }
    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null){
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = driver.findElements(By.name("entry"));

        if (elements != null && elements.size() > 0) {
            for (int i = 2; i < elements.size() + 2; i++) {
                String name = driver.findElement(By.xpath("*//tbody/tr[" + i + "]/td[3]")).getText();
                String surname = driver.findElement(By.xpath("*//tbody/tr[" + i + "]/td[2]")).getText();

                contactCache.add(new ContactData().withFirstname(name).withLastname(surname));
            }
        }
        return new Contacts(contactCache);
    }
}