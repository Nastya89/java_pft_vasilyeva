package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private String firstname;
    private String middlname;
    private String lastname;
    private String nickname;
    private String company;
    private String home;
    private String mobile;
    private String work;
    private String email;
    private String group;
    private int id;

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname);
    }

    public ContactData withMiddlname(String middlname) {
        this.middlname = middlname;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactData withHomePhone(String home) {
        this.home = home;
        return this;
    }

    public ContactData withMobilePhone(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ContactData withWorkPhone(String work) {
        this.work = work;
        return this;
    }
    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

   /* public ContactData(String firstname, String middlname, String lastname, String nickname, String company, String home, String email, String group) {
        this.firstname = firstname;
        this.middlname = middlname;
        this.lastname = lastname;
        this.nickname = nickname;
        this.company = company;
        this.home = home;
        this.email = email;
        this.group = group;
    }

    public ContactData(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }*/

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

    public String getHomePhone() {
        return home;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }
    public String getMobilePhone() {
        return mobile;
    }
    public String getWorkPhone() {
        return work;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    public String getGroup() {
        return group;
    }

}
