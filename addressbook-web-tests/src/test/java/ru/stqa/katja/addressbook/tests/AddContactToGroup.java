package ru.stqa.katja.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.katja.addressbook.model.Contact;
import ru.stqa.katja.addressbook.model.ContactData;
import ru.stqa.katja.addressbook.model.GroupData;
import ru.stqa.katja.addressbook.model.Groups;

import java.io.File;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class AddContactToGroup extends TestBase {

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
      app.contact().create(new ContactData().withFirstname("Donald").withLastname("Duck").withPhoto(photo));
      app.goTo().homePage();
    }

    Contact allContacts = app.db().contacts();
    Groups allGroups = app.db().groups();
    int allGroupsSize = allGroups.size();


    foundContact = null;
    Iterator<ContactData> i = allContacts.iterator();
    while (i.hasNext()) {
      ContactData с = i.next();
      if (с.getGroups().size() < allGroupsSize) {
        foundContact = с;
        GroupData selectedGroup  = app.contact().findUniqueGroup(foundContact, allGroups);
        foundGroup = selectedGroup;
        break;
      }

      } if (foundContact== null){
      app.goTo().groupPage();
      GroupData newGroup = new GroupData().withName("UniqueGroup").withHeader("UniqueHeader").withFooter("UniqueFooter");
      app.group().create(newGroup);
      Groups allGroupsAfter = app.db().groups();
      foundGroup = newGroup.withId(allGroupsAfter.stream().mapToInt((g) -> g.getId()).max().getAsInt());
      foundContact = allContacts.iterator().next();
      }
    }

    @Test
    public void testAddContactToGroup() {
      app.goTo().homePage();
      app.contact().addToGroup(foundContact, foundGroup);
      app.goTo().homePage();
      int idOfFoundContact = foundContact.getId();
      ContactData foundContactAfter = app.db().contactByID(idOfFoundContact).iterator().next();
      assertThat(foundContactAfter.getGroups(), hasItem(foundGroup));
    }
}
