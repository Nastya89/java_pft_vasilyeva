package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDetailedForm extends TestBase {
    @Test
    public void testContactDetailedForm() {
        app.contact().goToHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        app.contact().selectContactById(contact.getId());
        app.contact().initContactDetails(contact.getId());
        assertThat(app.contact().getAllText(), equalTo(mergeDetails(contactInfoFromEditForm)));
    }

    private String mergeDetails(ContactData contact) {
        String fullName = Arrays.asList(contact.getFirstname(), contact.getLastname())
                .stream().filter((s) -> !s.equals(""))
                .collect(Collectors.joining(" "));

        return Arrays.asList(fullName, contact.getAddress(),
                contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(),
                contact.getEmail(), contact.getEmail2(), contact.getEmai3())
                .stream().filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }
}
