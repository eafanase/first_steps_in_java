package ru.stqa.katja.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.katja.addressbook.model.ContactData;
import ru.stqa.katja.addressbook.model.GroupData;

import java.util.ArrayList;
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
    type(By.name("email"), contactData.getEmail());
    click(By.name("bday"));
    click(By.name("bmonth"));
    click(By.name("byear"));
    type(By.name("byear"), contactData.getByear());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
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

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));

  }

  public void initContactModification() {
    click(By.xpath("(//img[@alt='Edit'])"));
  }

  public void submitContactModification() {
    click(By.xpath("(//input[@name='update'])[2]"));
  }

  public void createContact(ContactData contact) {
    addNewContact();
    fillContactForm(contact, true);
    submitContactForm();
    }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.cssSelector("input[type='checkbox'][name='selected[]']"));
    for (WebElement element : elements) {
      String firstname = element.getText();
      ContactData contact = new ContactData(firstname,null,null,null,null,null,null,null,null,null);
      contacts.add(contact);
    }
    return contacts;
  }
}
