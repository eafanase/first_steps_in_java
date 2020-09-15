package ru.stqa.katja.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.katja.addressbook.model.ContactData;
import ru.stqa.katja.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTest extends TestBase {

  @Test
  public void testContactModification(){
    app.navigationHelper.gotoHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Donald", "Duck", null, null, null, null, null, null, null,null));
      app.navigationHelper.gotoHomePage();
    }
//    int before = app.getContactHelper().getContactCount();
    List<ContactData> before= app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size()-1);
    app.getContactHelper().initContactModification();
    ContactData contact = new ContactData(before.get(before.size()-1).getId(),"Donald", "Duck", "DuckCompany", "Disneyland 111", "777-777-888", "duck@duck.com", "9", "August", "1928","test1");
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.navigationHelper.gotoHomePage();
//    int after = app.getContactHelper().getContactCount();
    List<ContactData> after= app.getContactHelper().getContactList();
//    Assert.assertEquals(after, before);
    Assert.assertEquals(after.size(), before.size());

  before.remove(before.size()-1);
  before.add(contact);
    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));


  }
}
