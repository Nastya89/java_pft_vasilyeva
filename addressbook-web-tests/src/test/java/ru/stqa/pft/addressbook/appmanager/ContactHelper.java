package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver driver) {
        super(driver);
    }


    public void selectContact(int index) {
        driver.findElements(By.name("selected[]")).get(index).click();

    }

    public void selectContactByName(ContactData contact) {
        driver.findElement(By.xpath(
                String.format("*//input[contains(@title,'%s %s'] ", contact.getFirstname(), contact.getLastname()))
        ).click();

    }

    public void selectContactById(int id) {
        driver.findElement(By.id((String.valueOf(id)))).click();

    }

    public void initContactModification(int id) {
        click(By.xpath(String.format("*//a[contains(@href, '%s')]/img[@title = 'EDIT']", id)));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("company"), contactData.getCompany());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
            ContactData withGroup = contactData.withGroup("Test1");
            new Select(driver.findElement(By.name("new_group"))).selectByVisibleText((withGroup.getGroup()));
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = driver.findElement(By.name("firstname")).getAttribute("value");
        String lastname = driver.findElement(By.name("lastname")).getAttribute("value");
        String home = driver.findElement(By.name("home")).getAttribute("value");
        String mobile = driver.findElement(By.name("mobile")).getAttribute("value");
        String work = driver.findElement(By.name("work")).getAttribute("value");
        String address = driver.findElement(By.name("address")).getAttribute("value");
        goToHomePage();
        return new ContactData().withId(contact.getId())
                .withFirstname(firstname).withLastname(lastname).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withAddress(address);
    }

    private void initContactModificationById(int id) {
        WebElement checkbox = driver.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();
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
        contactCache = null;
        goToHomePage();
    }

    public void modify(ContactData contactToModify, ContactData dataForModification) {
        selectContactById(contactToModify.getId());
        initContactModification(contactToModify.getId());
        fillContactForm(dataForModification
                , false);
        submitContactModification();
        contactCache = null;
        goToHomePage();
    }

    public void deleteContact(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        acceptAlert();
        contactCache = null;
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
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> rows = driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String allPhones = cells.get(5).getText();
            String address = cells.get(3).getText();
            contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                   .withAllPhones(allPhones).withAddress(address));
        }
        return new Contacts(contactCache);
    }
}