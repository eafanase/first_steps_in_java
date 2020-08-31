package ru.stqa.katja.addressbook;

import org.testng.annotations.*;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {
    gotoGroupPage("groups");
    initGroupCreation("new");
    fillGroupForm(new GroupData("test1", "TEST2", "TEST3"));
    submitGroupCreation("submit");
    returntoGroupPage("group page");
    gotoGroupPage("groups");
    logout("Logout");
  }

}
