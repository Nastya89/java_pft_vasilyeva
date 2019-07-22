package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {
    @Test(enabled = false)
    public void testContactModification() {
        app.getContactHelper().goToHomePage();

        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Petro", "Petrovich", "Petrov", "Petya", "Volvo", "357781468", "petrov@mail.ua", "Test1"));
        }

        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);

        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Ivan", "Petrovich", "Petrov", "Ivan", "Volvo", "357781468", "petrov@mail.ua", null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().goToHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();

        after.add(new ContactData("Ivan", "Petrov"));

        before.sort(Comparator.comparing(ContactData::getFirstname).thenComparing(ContactData::getLastname));
        after.sort(Comparator.comparing(ContactData::getFirstname).thenComparing(ContactData::getLastname));

        if (before != null || before.size() != 0) {
            ContactData updatedContact = null;

            for (ContactData elAfter : after) {

                ContactData existedContact = before.stream().filter(e ->
                        e.getFirstname().equalsIgnoreCase(elAfter.getFirstname())
                                && e.getLastname().equalsIgnoreCase(elAfter.getLastname())).findFirst().orElse(null);

                if (existedContact == null) {
                    updatedContact = elAfter;
                    System.out.println("FirstName: " + updatedContact.getFirstname() + " LastName: " + updatedContact.getLastname());
                    break;
                }
            }
            Assert.assertNotEquals(updatedContact, null);
        }

        Assert.assertEquals(after.size(), before.size());
        System.out.println(before + " after: " + after);

        Assert.assertNotEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
