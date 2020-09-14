package ru.stqa.katja.addressbook.model;

import java.util.Objects;

public class ContactData {
  private final String firstname;
  private final String lastname;
  private final String company;
  private final String address;
  private final String phone;
  private final String email;
  private final String bday;
  private final String bmonth;
  private final String byear;
  private String group;

  public ContactData(String firstname, String lastname, String company, String address, String phone, String email, String bday, String bmonth, String byear, String group) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.company = company;
    this.address = address;
    this.phone = phone;
    this.email = email;
    this.bday = bday;
    this.bmonth = bmonth;
    this.byear = byear;
    this.group = group;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getCompany() {
    return company;
  }

  public String getAddress() {
    return address;
  }

  public String getPhone() {
    return phone;
  }

  public String getEmail() {
    return email;
  }

  public String getBday() {
    return bday;
  }

  public String getBmonth() {
    return bmonth;
  }

  public String getByear() {
    return byear;
  }
// Этот метод не работает, т.к. нужно сравнивать объекты
//  @Override
//  public String toString() {
//    return "ContactData{" +
//            "firstname='" + firstname + '\'' +
//            ", lastname='" + lastname + '\'' +
//            ", company='" + company + '\'' +
//            ", address='" + address + '\'' +
//            ", phone='" + phone + '\'' +
//            ", email='" + email + '\'' +
//            ", bday='" + bday + '\'' +
//            ", bmonth='" + bmonth + '\'' +
//            ", byear='" + byear + '\'' +
//            ", group='" + group + '\'' +
//            '}';
//  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname) &&
            Objects.equals(company, that.company) &&
            Objects.equals(address, that.address) &&
            Objects.equals(phone, that.phone) &&
            Objects.equals(email, that.email) &&
            Objects.equals(bday, that.bday) &&
            Objects.equals(bmonth, that.bmonth) &&
            Objects.equals(byear, that.byear) &&
            Objects.equals(group, that.group);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstname, lastname, company, address, phone, email, bday, bmonth, byear, group);
  }

  public String getGroup() {
    return group;
  }
}
