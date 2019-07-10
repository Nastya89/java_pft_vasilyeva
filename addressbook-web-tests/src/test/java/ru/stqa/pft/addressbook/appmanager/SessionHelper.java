package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {

    public SessionHelper(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        type(By.name("user"), username);
        type(By.name("pass"), password);

        click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='PASSWORD:'])[1]/following::input[2]"));
        click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='IMPORT'])[1]/following::h1[1]"));
    }
}
