package ru.stqa.katja.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.katja.addressbook.model.Contact;
import ru.stqa.katja.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {


  @Test
  public void testContactCreation() {
    Contact before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Donald").withLastname("Duck").withGroup("test1");
    app.contact().create(contact);
    app.goTo().homePage();
    Contact after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));


 //   before.add(contact);
//    Assert.assertEquals(before, after);
    assertThat(after, equalTo
            (before.withAdded(contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
 //   app.sessionHelper.logout();
  }
}
