package ru.stqa.katja.addressbook;

import org.testng.annotations.Test;

public class GroupDeletionTest extends TestBase{

  @Test
  public void testGroupDeletion() throws Exception {
    gotoGroupPage("groups");
    selectGroup();
    deleteSelectedGroups();
    returntoGroupPage("group page");
  }

}
