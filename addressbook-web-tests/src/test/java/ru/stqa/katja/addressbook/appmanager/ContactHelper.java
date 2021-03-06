package ru.stqa.katja.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.katja.addressbook.model.Contact;
import ru.stqa.katja.addressbook.model.ContactData;
import ru.stqa.katja.addressbook.model.GroupData;
import ru.stqa.katja.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ContactHelper extends HelperBase {


  public ContactHelper(WebDriver wd) {

    super(wd);
  }

  public void submitContactForm() {
    submitContactForm(By.xpath("(//input[@name='submit'])[2]"));
  }

  private void submitContactForm(By xpath) {
    wd.findElement(xpath).click();
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    click(By.name("home"));
    type(By.name("home"), contactData.getPhone());
    type(By.name("mobile"), contactData.getMobphone());
    type(By.name("work"), contactData.getWorkphone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    click(By.name("bday"));
    click(By.name("bmonth"));
    click(By.name("byear"));
    type(By.name("byear"), contactData.getByear());
    attach(By.name("photo"), contactData.getPhoto());

    if (creation) {
      if (contactData.getGroups().size() > 0){
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

  }

  public void addNewContact() {
    click(By.linkText("add new"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
    // click(By.name("selected[]"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value= '" + id + "']")).click();
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));

  }

  public void initContactModification(int index) {
    wd.findElements(By.xpath("(//img[@alt='Edit'])")).get(index).click();
  }

  public void initContactModificationById(int id) {
    wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();

  }

  public void submitContactModification() {
    click(By.xpath("(//input[@name='update'])[2]"));
  }

  public void create(ContactData contact) {
    addNewContact();
    fillContactForm(contact, true);
    submitContactForm();
    contactCache = null;
  }

  public void modify(int index, ContactData contact) {
    initContactModification(index);
    fillContactForm(contact, false);
    submitContactModification();

  }

  public void modify(ContactData contact) {
    initContactModificationById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
  }

  public void addToGroup(ContactData contact, GroupData group) {
    selectContactById(contact.getId());
    addContactTogroup(contact, group);

  }
   private void addContactTogroup(ContactData contactData, GroupData group) {
 //   new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
    new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(group.getName());
    click(By.xpath("(//input[@value='Add to'])"));
  }

  public void deleteFromGroup(ContactData deletedContact, GroupData deletedGroup) {
    selectGroupOnHomePage(deletedGroup);
    selectContactById(deletedContact.getId());
    click(By.xpath("(//input[@name='remove'])"));

  }

  private void selectGroupOnHomePage(GroupData deletedGroup) {
    new Select(wd.findElement(By.name("group"))).selectByVisibleText(deletedGroup.getName());
  }

  public void selectAllOnHomePage() {
    new Select(wd.findElement(By.name("group"))).selectByVisibleText("[all]");

  }

  public void delete(int index) {
    selectContact(index);
    deleteSelectedContact();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    contactCache = null;
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.cssSelector("[name='entry']"));
    for (WebElement element : elements) {
      String firstname = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
      String lastname = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
    }
    return contacts;
  }

  private Contact contactCache = null;

  public Contact all() {
    if (contactCache != null) {
      return new Contact(contactCache);
    }

    contactCache = new Contact();
    List<WebElement> elements = wd.findElements(By.cssSelector("[name='entry']"));
    for (WebElement element : elements) {
      String firstname = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
      String lastname = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String allPhones = element.findElement(By.cssSelector("td:nth-child(6)")).getText();
      String allAdresses = element.findElement(By.cssSelector("td:nth-child(4)")).getText();
      String allEmails = element.findElement(By.cssSelector("td:nth-child(5)")).getText();
      //       String[] phones = element.findElement(By.cssSelector("td:nth-child(6)")).getText().split("\n");
      contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).
              withAllPhones(allPhones).withAddress(allAdresses).withAllEmails(allEmails));
    }
    return contactCache;
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String phone = wd.findElement(By.name("home")).getAttribute("value");
    String mobphone = wd.findElement(By.name("mobile")).getAttribute("value");
    String workphone = wd.findElement(By.name("work")).getAttribute("value");
    String allAddresses = wd.findElement(By.name("address")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).
            withPhone(phone).withMobphone(mobphone).withWorkphone(workphone)
            .withAddress(allAddresses).withEmail(email).withEmail2(email2).withEmail3(email3);
  }


  public GroupData findUniqueGroup(ContactData foundContact, Groups allGroups) {
    GroupData selectedGroup = null;
    Iterator<GroupData> i = allGroups.iterator();
    while (i.hasNext()) {
      GroupData g = i.next();
      if (!foundContact.getGroups().contains(g)) {
        selectedGroup = g;
        break;
      }
    }
    return selectedGroup;
  }
}
