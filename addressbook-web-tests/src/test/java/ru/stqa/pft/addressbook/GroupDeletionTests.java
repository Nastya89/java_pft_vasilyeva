package ru.stqa.pft.addressbook;


import org.testng.annotations.Test;


public class GroupDeletionTests extends TestBase {
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Test
    public void testGroupDeletion() throws Exception {
        goToGroupPage();
        selectGroup();
        deleteSelectedGroups();
        returnToGroupPage();
    }

}