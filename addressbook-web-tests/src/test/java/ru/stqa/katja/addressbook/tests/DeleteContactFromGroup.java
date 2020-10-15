package ru.stqa.katja.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.katja.addressbook.model.Contact;
import ru.stqa.katja.addressbook.model.ContactData;
import ru.stqa.katja.addressbook.model.GroupData;
import java.io.File;
import java.util.*;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactFromGroup extends TestBase {
  public ContactData foundContact;
  public GroupData foundGroup;

  @BeforeMethod
  public void searchGroupAndContact() {

    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
    app.goTo().homePage();
    File photo = new File("src/test/resources/avatar.png");
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Donald").withLastname("Duck").withPhoto(photo).inGroup(app.db().groups().iterator().next()));
      app.goTo().homePage();
    }

    Contact allContacts = app.db().contacts();

    foundContact = null;
    Iterator<ContactData> i = allContacts.iterator();
    while (i.hasNext()) {
      ContactData с = i.next();
      if (с.getGroups().size() > 0) {
        foundContact = с;
        foundGroup = foundContact.getGroups().iterator().next();
        break;
      }

    } if (foundContact== null){
      foundContact = allContacts.iterator().next();
      foundGroup = app.db().groups().iterator().next();
      app.contact().addToGroup(foundContact, foundGroup);
    }
  }

  @Test
  public void testDeleteContactFromGroup() {
    app.goTo().homePage();
    app.contact().deleteFromGroup(foundContact, foundGroup);
    int idOfDeletedContact = foundContact.getId();
    ContactData deletedContactAfter = app.db().contactByID(idOfDeletedContact).iterator().next();
    assertThat(deletedContactAfter.getGroups(), not(hasItem(foundGroup)));
  }
}



