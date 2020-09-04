package ru.stqa.katja.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase {

  @Test
  public void testContactDeletion() {
    app.navigationHelper.gotoHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.navigationHelper.closeAlert();

      }
}
