package ru.stqa.pft.addressbook.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.CoreMatchers;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactPhoneTests extends TestBase {

    @Test
    public void testContactPhones() {
        app.contact().goToHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getHomePhone(), CoreMatchers.equalTo(contactInfoFromEditForm.getHomePhone()));
        assertThat(contact.getMobilePhone(), CoreMatchers.equalTo(contactInfoFromEditForm.getMobilePhone()));
        assertThat(contact.getWorkPhone(), CoreMatchers.equalTo(contactInfoFromEditForm.getWorkPhone()));
    }

}
