package ru.stqa.katja.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.katja.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTest extends TestBase {

  @Test
  public void testContactDeletion() {
    app.navigationHelper.gotoHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Donald", "Duck", "DuckCompany", "Disneyland 111", "777-777-888", "duck@duck.com", "9", "August", "1928","test1"));
      app.navigationHelper.gotoHomePage();
         }
//    int before = app.getContactHelper().getContactCount();
    List<ContactData> before= app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size()-1);
    app.getContactHelper().deleteSelectedContact();
    app.navigationHelper.closeAlert();
    app.navigationHelper.gotoHomePage();
//    int after = app.getContactHelper().getContactCount();
    List<ContactData> after = app.getContactHelper().getContactList();
//    Assert.assertEquals(after, before-1);
    Assert.assertEquals(after.size(), before.size()-1);
      }
}
