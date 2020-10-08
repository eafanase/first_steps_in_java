package ru.stqa.katja.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.katja.addressbook.model.ContactData;
import ru.stqa.katja.addressbook.model.GroupData;
import ru.stqa.katja.addressbook.model.Groups;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.contains;

public class AddContactToGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditionsContact() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
    app.goTo().homePage();
    Groups groups = app.db().groups();
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Donald").withLastname("Duck"));
      app.goTo().homePage();
    }
  }

  @Test
  public void testAddContactToGroup() {
    ContactData addedContact = app.db().contacts().iterator().next();
      GroupData addedGroup = app.db().groups().iterator().next();
    if (addedContact.getGroups().contains(addedGroup)) {
      app.goTo().homePage();
       app.contact().deleteFromGroup(addedContact, addedGroup);
      app.goTo().homePage();
      app.contact().selectAllOnHomePage();
         }
     app.contact().addToGroup(addedContact, addedGroup);
    int idOfaddedContact = addedContact.getId();
    ContactData addedContactAfter =app.db().contactByID(idOfaddedContact).iterator().next();
    assertThat(Arrays.asList(addedContactAfter.getGroups()), contains(addedGroup));

    }

}
