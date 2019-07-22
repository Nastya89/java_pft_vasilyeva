package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {
    @Test(enabled = false)
    public void testContactDeletion() {
        app.getContactHelper().goToHomePage();

        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Petr", "Petrovich", "Petrov", "Petya", "Volvo", "357781468", "petrov@mail.ua", "Test1"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        int lastElementIndex = before.size() - 1;

        app.getContactHelper().selectContact(lastElementIndex);
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().acceptAlert();
      //  app.getContactHelper().waitForPageLoad();
        app.getContactHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();

        System.out.println(before.size() + " after: " + after.size());

        Assert.assertEquals(after.size(), before.size() - 1);

           before.remove(lastElementIndex);

         Assert.assertEquals(before, after);

    }
}
