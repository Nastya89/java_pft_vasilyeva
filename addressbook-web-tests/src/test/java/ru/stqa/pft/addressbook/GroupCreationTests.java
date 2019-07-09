package ru.stqa.pft.addressbook;

import org.testng.annotations.*;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {

        goToGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupData("Test1", "Test1", "Test1"));
        submitGroupCreation();
        goToGroupPage();
    }

}
