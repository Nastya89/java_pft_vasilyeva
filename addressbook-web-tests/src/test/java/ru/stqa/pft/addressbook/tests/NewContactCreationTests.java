package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NewContactCreationTests extends TestBase {

    @Test
    public void testNewContactCreationTests() throws Exception {

        app.contact().goToHomePage();
        Contacts before = app.contact().all();
        app.contact().goToNewContactCreationPage();
        File photo= new File("src/test/resources/stru.png");
        ContactData contact = new ContactData().withFirstname("Ivan").withLastname("Petrov").withPhoto(photo);
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();

        System.out.println(before.size() + " after: " + after.size());

        if (before != null || before.size() != 0) {
            ContactData newContact = null;

            for (ContactData elAfter : after) {

                ContactData existedContact = before.stream().filter(e ->
                        e.getFirstname().equalsIgnoreCase(elAfter.getFirstname())
                                && e.getLastname().equalsIgnoreCase(elAfter.getLastname())).findFirst().orElse(null);

                if (existedContact == null) {
                    newContact = elAfter;
                    System.out.println("FirstName: " + newContact.getFirstname() + " LastName: " + newContact.getLastname());
                    break;
                }
            }
            Assert.assertNotEquals(newContact, null);
            assertThat(after, equalTo(before.withAdded(contact)));
        }
    }
}