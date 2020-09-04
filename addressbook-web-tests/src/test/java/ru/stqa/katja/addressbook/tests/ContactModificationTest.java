package ru.stqa.katja.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.katja.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase {

  @Test
  public void testContactModification(){
    app.navigationHelper.gotoHomePage();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Donald", "Duck", "DuckCompany", "Disneyland 12", "777-777-888", "duck@duck.com", "9", "August", "1928"));
    app.getContactHelper().submitContactModification();
    app.navigationHelper.gotoHomePage();

  }
}
