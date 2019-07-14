package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class NewContactCreationTests extends TestBase {

    @Test
    public void testNewContactCreationTests() throws Exception {

        app.goToNewContactCreationPage();
        app.getContactHelper().fillContactForm(new ContactData("Petro", "Petrovich", "Petrov", "Petya", "Volvo", "357781468", "petrov@mail.ua", "Test1"),true);
        app.submitContactCreation();
    }
}
