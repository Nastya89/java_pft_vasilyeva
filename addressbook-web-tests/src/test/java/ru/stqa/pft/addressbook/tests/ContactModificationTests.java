package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Ivan", "Petrovich", "Petrov", "Ivan", "Volvo", "357781468", "petrov@mail.ua",null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();
    }
}
