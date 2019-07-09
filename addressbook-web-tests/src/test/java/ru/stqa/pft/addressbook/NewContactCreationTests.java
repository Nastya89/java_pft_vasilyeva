package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class NewContactCreationTests extends TestBase {

    @Test
    public void testNewContactCreationTests() throws Exception {

        goToNewContactCreationPage();
        fillContactForm(new ContactData("Petro", "Petrovich", "Petrov", "Petya", "Volvo", "357781468", "petrov@mail.ua"));
        submitContactCreation();
    }
}
