package ru.stqa.katja.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.katja.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.navigationHelper.gotoHomePage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData("Donald", "Duck", null, null, null, null, null, null, null, null));
      app.navigationHelper.gotoHomePage();
    }
  }

  @Test
  public void testContactDeletion() {
    List<ContactData> before= app.contact().list();
    int index = before.size()-1;
    app.contact().delete(index);
    app.navigationHelper.closeAlert();
    app.navigationHelper.gotoHomePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size()-1);

    before.remove(index);
    Assert.assertEquals(before, after);
      }


}
