package ru.stqa.katja.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.katja.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTest extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
//    int before = app.getContactHelper().getContactCount();
    List<ContactData> before= app.getContactHelper().getContactList();
    app.getContactHelper().addNewContact();
    ContactData contact = new ContactData("Donald", "Duck", null, null, null, null, null, null, null,null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactForm();
    app.navigationHelper.gotoHomePage();
//    int after = app.getContactHelper().getContactCount();
    List<ContactData> after = app.getContactHelper().getContactList();
//    Assert.assertEquals(after, before+1);
    Assert.assertEquals(after.size(), before.size()+1);

    int max =0;
    for (ContactData c: after){
      if (c.getId()> max) {
        max = c.getId();
      }
    }
    contact.setId(max);
    before.add(contact);
    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
    app.sessionHelper.logout();
  }
}
