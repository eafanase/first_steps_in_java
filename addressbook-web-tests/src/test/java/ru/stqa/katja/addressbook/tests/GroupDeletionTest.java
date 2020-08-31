package ru.stqa.katja.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTest extends TestBase{

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().gotoGroupPage("groups");
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returntoGroupPage("group page");
  }

}
