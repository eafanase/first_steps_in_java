package ru.stqa.katja.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.katja.addressbook.model.Contact;
import ru.stqa.katja.addressbook.model.ContactData;
import ru.stqa.katja.addressbook.model.GroupData;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditionsContact() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
    app.goTo().homePage();
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Donald").withLastname("Duck"));
      app.goTo().homePage();
    }
  }

  @Test
  public void testAddContactToGroup() {
    Contact addedContact = app.db().contacts();
    GroupData addedGroup = app.db().groups().iterator().next();









//    List<ContactData> allContacts = new ArrayList<ContactData>(app.db().contacts());
//    GroupData addedGroup = app.db().groups().iterator().next();
//    for (ContactData Contact : allContacts) {
//      if (!Contact.getGroups().contains(addedGroup)) {
//        app.goTo().homePage();
//        app.contact().addToGroup(Contact, addedGroup);
//
//      } else {
//        File photo = new File("src/test/resources/avatar.png");
//        ContactData contact = new ContactData().withFirstname("Minni").withLastname("Mouse").withPhoto(photo);
//        app.contact().create(contact);
//        app.goTo().homePage();
//        Contact after = app.db().contacts();
//        app.contact().addToGroup(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()), addedGroup);

      }

 //     int idOfaddedContact = Contact.getId();
  //    ContactData addedContactAfter = app.db().contactByID(idOfaddedContact).iterator().next();
  //    assertThat(addedContactAfter.getGroups(), hasItem(addedGroup));
    }


    //  ContactData addedContact = app.db().contacts().iterator().next();
//
//    if (addedContact.getGroups().contains(addedGroup)) {
//
//      for (Object Contact:allContacts){
//        if (allContacts.toString().contains((CharSequence) addedGroup)){
//         app.contact().create(new ContactData());
//          app.contact().addToGroup(addedContact, addedGroup);
//
//        }
//      }
//
// //     app.goTo().homePage();
// //     app.contact().deleteFromGroup(addedContact, addedGroup);
// //     app.goTo().homePage();
// //     app.contact().selectAllOnHomePage();
//    }
//    app.contact().addToGroup(addedContact, addedGroup);
//    int idOfaddedContact = addedContact.getId();
//    ContactData addedContactAfter = app.db().contactByID(idOfaddedContact).iterator().next();
//    assertThat(addedContactAfter.getGroups(), hasItem(addedGroup));

//  }

//}
