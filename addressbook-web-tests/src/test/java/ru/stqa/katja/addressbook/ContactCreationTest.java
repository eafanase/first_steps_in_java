package ru.stqa.katja.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTest extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    addNewContact("add new");
    fillContactForm(new ContactData("Donald", "Duck", "DuckCompany", "Disneyland 111", "777-777-888", "duck@duck.com", "9", "August", "1928"));
    submitContactForm();
    gotoHomePage("home");
    logout("Logout");
  }
  }
