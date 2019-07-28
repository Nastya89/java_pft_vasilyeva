package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.contact().goToHomePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Ivan").withLastname("Petrov"));
        }
    }

    @Test
    public void testContactModification() {
        Contacts beforeList = app.contact().all();
        ContactData foundedContact = beforeList.iterator().next();
        ContactData modifiedContact = new ContactData()
                .withFirstname("Maria").withLastname("Lopez").withNickname("tester").withCompany("Toyota");
        app.contact().modify(foundedContact, modifiedContact);

        Contacts afterList = app.contact().all();
        afterList.add(modifiedContact);

        if (beforeList != null || beforeList.size() != 0) {
            ContactData updatedContact = null;

            for (ContactData elAfter : afterList) {

                ContactData existedContact = beforeList.stream().filter(e ->
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
        assertThat(afterList, equalTo(beforeList.withOut(foundedContact).withAdded(modifiedContact)));
    }
}
