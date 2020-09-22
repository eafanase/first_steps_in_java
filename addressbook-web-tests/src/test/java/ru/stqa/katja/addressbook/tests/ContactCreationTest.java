package ru.stqa.katja.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.katja.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTest extends TestBase {


  @Test
  public void testContactCreation() {
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Donald").withLastname("Duck").withGroup("test1");
    app.contact().create(contact);
    app.goTo().homePage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
    before.add(contact);
//    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
//    before.sort(byId);
//    after.sort(byId);
    Assert.assertEquals(before, after);
 //   app.sessionHelper.logout();
  }
}
