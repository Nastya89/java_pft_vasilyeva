package ru.stqa.pft.addressbook.model;

import java.io.File;
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
    private String allPhones;
    private String allEmails;
    private String address;
    private String group;
    private String email;
    private String email2;
    private String email3;
    private int id;
    private File photo;


    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData contactData = (ContactData) o;
        return  this.id == contactData.id &&
                Objects.equals(this.firstname, contactData.firstname) &&
                Objects.equals(this.lastname, contactData.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname);
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

    public ContactData withAddress(String address) {
        this.address = address;
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

    public String getAllPhones() {
        return allPhones;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
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
    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }
    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }
    public ContactData withEmail3(String email3) {
        this.email3= email3;
        return this;
    }
    public ContactData withPhoto(File photo) {
        this.photo = photo;
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

    public String getAllEmails() { return allEmails; }

    public int getId() { return id; }

    public String getMobilePhone() {
        return mobile;
    }

    public String getWorkPhone() { return work; }

    public String getAddress() { return address; }

    public String getEmail() { return email; }

    public String getEmail2() { return email2; }

    public String getEmai3() { return email3; }

    public File getPhoto() { return photo; }
    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    public String getGroup() {
        return group;
    }

}
