package ru.stqa.katja.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table (name = "addressbook")
public class ContactData {
  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "firstname")
  private String firstname;

  @Expose
  @Column(name = "lastname")
  private String lastname;

  @Column(name = "company")
  private String company;

  @Column(name = "address")
  @Type(type = "text" )
  private String address;

  @Expose
  @Column(name = "home")
  @Type(type = "text" )
  private String phone;

  @Column(name = "mobile")
  @Type(type = "text" )
  private String mobphone;

  @Column(name = "work")
  @Type(type = "text" )
  private String workphone;

  @Transient
  private String allphones;

  @Column(name = "email")
  @Type(type = "text" )
  private String email;

  @Column(name = "email2")
  @Type(type = "text" )
  private String email2;

  @Column(name = "email3")
  @Type(type = "text" )
  private String email3;

  @Transient
  private String allemails;

  @Column(name = "bday", columnDefinition = "TINYINT")
  private String bday;

  @Column(name = "bmonth", columnDefinition = "TINYINT")
  private String bmonth;

  @Column(name = "byear", columnDefinition = "TINYINT")
   private String byear;

 // @Expose
 // @Transient
 // private String group;

  @Column(name = "photo")
  @Type(type = "text" )
  private String photo;

  @ManyToMany (fetch = FetchType.EAGER)
  @JoinTable (name = "address_in_groups",
          joinColumns = @JoinColumn (name = "id"), inverseJoinColumns = @JoinColumn (name = "group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();


  public File getPhoto() {
    return  new File(photo);
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public ContactData withEmail3(String email1) {
    this.email3 = email1;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withAllEmails(String allemails) {
    this.allemails = allemails;
    return this;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
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

  public ContactData withPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public ContactData withMobphone(String mobphone) {
    this.mobphone = mobphone;
    return this;
  }

  public ContactData withWorkphone(String workphone) {
    this.workphone = workphone;
    return this;
  }

  public ContactData withAllPhones(String allphones) {
    this.allphones = allphones;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withBday(String bday) {
    this.bday = bday;
    return this;
  }

  public ContactData withBmonth(String bmonth) {
    this.bmonth = bmonth;
    return this;
  }

  public ContactData withByear(String byear) {
    this.byear = byear;
    return this;
  }

//  public ContactData withGroup(String group) {
//    this.group = group;
//    return this;
//  }


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

  public String getMobphone() {
    return mobphone;
  }

  public String getWorkphone() {
    return workphone;
  }

  public String getAllphones() {
    return allphones;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail3() {
    return email3;
  }

  public String getEmail2() {
    return email2;
  }

  public String getAllEmails() {
    return allemails;
  }

  public String getBday() {
    return bday;
  }

  public int getId() {
    return id;
  }

  public Groups getGroups() {
    return new Groups(groups);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
    if (company != null ? !company.equals(that.company) : that.company != null) return false;
    if (address != null ? !address.equals(that.address) : that.address != null) return false;
    if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
    if (mobphone != null ? !mobphone.equals(that.mobphone) : that.mobphone != null) return false;
    if (workphone != null ? !workphone.equals(that.workphone) : that.workphone != null) return false;
    if (email != null ? !email.equals(that.email) : that.email != null) return false;
    if (email2 != null ? !email2.equals(that.email2) : that.email2 != null) return false;
    if (email3 != null ? !email3.equals(that.email3) : that.email3 != null) return false;
    return byear != null ? byear.equals(that.byear) : that.byear == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    result = 31 * result + (company != null ? company.hashCode() : 0);
    result = 31 * result + (address != null ? address.hashCode() : 0);
    result = 31 * result + (phone != null ? phone.hashCode() : 0);
    result = 31 * result + (mobphone != null ? mobphone.hashCode() : 0);
    result = 31 * result + (workphone != null ? workphone.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (email2 != null ? email2.hashCode() : 0);
    result = 31 * result + (email3 != null ? email3.hashCode() : 0);
    result = 31 * result + (byear != null ? byear.hashCode() : 0);
    return result;
  }

  public String getBmonth() {
    return bmonth;
  }

  public String getByear() {
    return byear;
  }

//  public String getGroup() {
//    return group;
//  }



  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", company='" + company + '\'' +
            ", address='" + address + '\'' +
            ", phone='" + phone + '\'' +
            ", mobphone='" + mobphone + '\'' +
            ", workphone='" + workphone + '\'' +
          //  ", allphones='" + allphones + '\'' +
            ", email='" + email + '\'' +
            ", email2='" + email2 + '\'' +
            ", email3='" + email3 + '\'' +
         //   ", allemails='" + allemails + '\'' +
         //   ", bday='" + bday + '\'' +
         //   ", bmonth='" + bmonth + '\'' +
            ", byear='" + byear + '\'' +
          //  ", group='" + group + '\'' +
            '}';
  }

  public ContactData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }
}
