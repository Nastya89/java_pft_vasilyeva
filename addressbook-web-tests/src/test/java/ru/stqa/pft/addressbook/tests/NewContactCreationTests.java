package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NewContactCreationTests extends TestBase {

    @Test
    public void testNewContactCreationTests() throws Exception {

        app.contact().goToHomePage();
        Contacts before = app.contact().all();
        app.contact().goToNewContactCreationPage();
        ContactData contact = new ContactData().withFirstname("Ivan").withLastname("Petrov");
        app.contact().create(contact);
        Contacts after = app.contact().all();

        //  before.sort(Comparator.comparing(ContactData::getFirstname).thenComparing(ContactData::getLastname));
        // after.sort(Comparator.comparing(ContactData::getFirstname).thenComparing(ContactData::getLastname));

        // УТОЧНИТЬ! При использовании HashSet результат теста не является валидным при сравнивании по имени и фамилии.
        //   Если добавляем не уникального пользователя, то кол-во контактов будет совпадать, что противоречит логике теста.

        assertThat(after.size(), equalTo(before.size() + 1));
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

            //    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
            assertThat(after, equalTo(before.withAdded(contact)));
        }
    }
}