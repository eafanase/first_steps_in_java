package ru.stqa.katja.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.katja.addressbook.model.Contact;
import ru.stqa.katja.addressbook.model.ContactData;
import ru.stqa.katja.addressbook.model.GroupData;
import ru.stqa.katja.addressbook.model.Groups;

public class AddContactToGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditionsContact(){
    if (app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }

    if (app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
  }
  @Test
  public void testAddContactToGroup() {
    ContactData addedContact = app.db().contacts().iterator().next();
    Groups GroupsByContact = app.db().groups();

    GroupData addedGroup = app.db().groups().iterator().next();
     app.goTo().homePage();
     app.contact().addToGroup(addedContact, addedGroup);




  }


}
