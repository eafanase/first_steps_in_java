package ru.stqa.katja.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.katja.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Donald").withLastname("Duck"));
      app.goTo().homePage();
    }
  }


  @Test
  public void testContactModification() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData().
            withId(before.get(index).getId()).withFirstname("Donald").withLastname("Duck").withCompany("DuckCompany").withAddress("Disneyland 111").
            withPhone("777-777-888").withEmail("duck@duck.com").withBday("9").withBmonth("August").withByear("1928").withGroup("test1");
    app.contact().modify(index, contact);
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }


}
