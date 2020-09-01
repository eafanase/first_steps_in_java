package ru.stqa.katja.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.katja.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage("groups");
    app.getGroupHelper().initGroupCreation("new");
    app.getGroupHelper().fillGroupForm(new GroupData("test1", "TEST2", "TEST3"));
    app.getGroupHelper().submitGroupCreation("submit");
    app.getGroupHelper().returntoGroupPage("group page");
    app.getNavigationHelper().gotoGroupPage("groups");
    app.sessionHelper.logout("Logout");
  }

}
