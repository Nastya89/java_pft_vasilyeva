package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class NewContactCreationTests extends TestBase {

    @Test(enabled = false)
    public void testNewContactCreationTests() throws Exception {

        app.getContactHelper().goToNewContactCreationPage();
        app.getContactHelper().goToHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData("Ivan11245", "Petrovich", "Petrov", "Petya", "Volvo", "357781468", "petrov@mail.ua", "Test1");
        app.getContactHelper().createContact(contact);
        List<ContactData> after = app.getContactHelper().getContactList();

        before.sort(Comparator.comparing(ContactData::getFirstname).thenComparing(ContactData::getLastname));
        after.sort(Comparator.comparing(ContactData::getFirstname).thenComparing(ContactData::getLastname));

        Assert.assertEquals(after.size(), before.size()+1);
        System.out.println(before.size() + " after: " + after.size());

        if(before != null || before.size() != 0){
            ContactData newContact = null;

            for(ContactData elAfter : after){

                ContactData existedContact =  before.stream().filter(e->
                        e.getFirstname().equalsIgnoreCase(elAfter.getFirstname())
                                && e.getLastname().equalsIgnoreCase(elAfter.getLastname())).findFirst().orElse(null);

                if(existedContact == null){
                    newContact = elAfter;
                    System.out.println("FirstName: " + newContact.getFirstname() + " LastName: " + newContact.getLastname());
                    break;
                }
            }
            Assert.assertNotEquals(newContact, null);
        }

        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
