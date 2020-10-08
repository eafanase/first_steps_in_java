package ru.stqa.katja.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.katja.addressbook.model.Contact;
import ru.stqa.katja.addressbook.model.ContactData;
import ru.stqa.katja.addressbook.model.GroupData;
import ru.stqa.katja.addressbook.model.Groups;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;

public class DeleteContactFromGroup extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
    app.goTo().homePage();
    File photo = new File("src/test/resources/avatar.png");
    Groups groups = app.db().groups();
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Donald").withLastname("Duck").withPhoto(photo).inGroup((groups.iterator().next())));
      app.goTo().homePage();
    }
  }

  @Test
  public void testDeleteContactFromGroup() {
    Groups groups = app.db().groups();
    ContactData deletedContact = app.db().contacts().iterator().next();
    if (deletedContact.getGroups().size() > 0) {
      app.goTo().homePage();
      app.contact().deleteFromGroup(deletedContact);
    } else {
      GroupData addedGroup = app.db().groups().iterator().next();
      app.contact().addToGroup((deletedContact.inGroup((groups.iterator().next()))), addedGroup);
      app.goTo().homePage();
      app.contact().deleteFromGroup(deletedContact);
    }

    String deletedGroup = deletedContact.getGroups().iterator().next().getName();
  //  Set<ContactData> list = new HashSet<ContactData>(app.contact().toString());
    MatcherAssert.assertThat(deletedContact.getGroups(), contains(not(deletedGroup)));





  }
}



