package ru.stqa.katja.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.katja.addressbook.model.ContactData;

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

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    click(By.name("theform"));
    click(By.name("home"));
    type(By.name("home"),contactData.getPhone());
    type(By.name("email"),contactData.getEmail());
    click(By.name("bday"));
    click(By.name("bmonth"));
    click(By.name("byear"));
    type(By.name("byear"), contactData.getByear());
      }

  public void addNewContact(String s) {
    wd.findElement(By.linkText(s)).click();
  }
}
