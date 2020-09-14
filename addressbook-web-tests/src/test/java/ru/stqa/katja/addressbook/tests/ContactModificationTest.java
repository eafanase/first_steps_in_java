package ru.stqa.katja.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.katja.addressbook.model.ContactData;

import java.util.List;

public class ContactModificationTest extends TestBase {

  @Test
  public void testContactModification(){
    app.navigationHelper.gotoHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Donald", "Duck", "DuckCompany", "Disneyland 111", "777-777-888", "duck@duck.com", "9", "August", "1928","test1"));
      app.navigationHelper.gotoHomePage();
    }
//    int before = app.getContactHelper().getContactCount();
    List<ContactData> before= app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size()-1);
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Donald", "Duck", "DuckCompany", "Disneyland 12", "777-777-888", "duck@duck.com", "9", "August", "1928", null), false);
    app.getContactHelper().submitContactModification();
    app.navigationHelper.gotoHomePage();
//    int after = app.getContactHelper().getContactCount();
    List<ContactData> after= app.getContactHelper().getContactList();
//    Assert.assertEquals(after, before);
    Assert.assertEquals(after.size(), before.size());

  }
}
