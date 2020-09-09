package ru.stqa.katja.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.katja.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData("test1", "Test2", "test3"));
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returntoGroupPage();
    app.getNavigationHelper().gotoGroupPage();
    app.sessionHelper.logout();
  }

}
