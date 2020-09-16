package ru.stqa.katja.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.katja.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase {

  @Test
  public void testContactModification() {
    app.navigationHelper.gotoHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Donald", "Duck", null, null, null, null, null, null, null, null));
      app.navigationHelper.gotoHomePage();
    }
//    int before = app.getContactHelper().getContactCount();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactModification(before.size() - 1);
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"Donald", "Duck", "DuckCompany", "Disneyland 111", "777-777-888", "duck@duck.com", "9", "August", "1928", "test1");
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.navigationHelper.gotoHomePage();
//    int after = app.getContactHelper().getContactCount();
    List<ContactData> after = app.getContactHelper().getContactList();
//    Assert.assertEquals(after, before);
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);        //сортировка первого списка
    after.sort(byId);         //сортировка второго списка
//    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
    Assert.assertEquals(before, after);

  }
}
