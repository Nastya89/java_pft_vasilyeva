package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
                .withFirstname("Ivann").withLastname("PetrovASAS1").withNickname("tester").withCompany("Toyota");
        app.contact().modify(foundedContact, modifiedContact);

        Contacts afterList = app.contact().all();
        afterList.add(modifiedContact);

        // before.sort(Comparator.comparing(ContactData::getFirstname).thenComparing(ContactData::getLastname));
        //  after.sort(Comparator.comparing(ContactData::getFirstname).thenComparing(ContactData::getLastname));

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

        // assertEquals(afterList.size(), beforeList.size());
        // System.out.println(beforeList.size() + " after: " + afterList.size());

        assertThat(afterList, equalTo(beforeList.withOut(foundedContact).withAdded(modifiedContact)));
    }
}
