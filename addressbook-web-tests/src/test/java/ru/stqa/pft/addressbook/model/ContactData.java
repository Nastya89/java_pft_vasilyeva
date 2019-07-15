package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String firstname;
    private final String middlname;
    private final String lastname;
    private final String nickname;
    private final String company;
    private final String home;
    private final String email;
    private String group;

    public ContactData(String firstname, String middlname, String lastname, String nickname, String company, String home, String email, String group) {
        this.firstname = firstname;
        this.middlname = middlname;
        this.lastname = lastname;
        this.nickname = nickname;
        this.company = company;
        this.home = home;
        this.email = email;
        this.group = group;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlname() {
        return middlname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCompany() {
        return company;
    }

    public String getHome() {
        return home;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }

}
