package ru.stqa.katja.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.katja.addressbook.model.ContactData;
import ru.stqa.katja.addressbook.model.GroupData;
import ru.stqa.katja.addressbook.model.Groups;
import java.io.File;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactFromGroup extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
    app.goTo().homePage();
    File photo = new File("src/test/resources/avatar.png");
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Donald").withLastname("Duck").withPhoto(photo));
      app.goTo().homePage();
    }
  }

  @Test
  public void testDeleteContactFromGroup() {
    Groups groups = app.db().groups();
    ContactData deletedContact = app.db().contacts().iterator().next();
    GroupData deletedGroup = groups.iterator().next();
    if (deletedContact.getGroups().contains((deletedGroup))) {

    } else {
      app.contact().addToGroup((deletedContact.inGroup((groups.iterator().next()))), deletedGroup);
    }

    app.goTo().homePage();
    app.contact().deleteFromGroup(deletedContact, deletedGroup);
    int idOfDeletedContact = deletedContact.getId();
    ContactData deletedContactAfter = app.db().contactByID(idOfDeletedContact).iterator().next();
    assertThat(deletedContactAfter.getGroups(), not(hasItem(deletedGroup)));

  }
}



