package ru.stqa.katja.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.katja.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().addNewContact();
    app.getContactHelper().fillContactForm(new ContactData("Donald", "Duck", "DuckCompany", "Disneyland 111", "777-777-888", "duck@duck.com", "9", "August", "1928","test1"), true);
    app.getContactHelper().submitContactForm();
    app.navigationHelper.gotoHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before+1);
    app.sessionHelper.logout();
  }
}
