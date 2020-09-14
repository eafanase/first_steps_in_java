package ru.stqa.katja.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.katja.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTest extends TestBase{

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", "TEST2", "TEST3"));
    }
//    int before = app.getGroupHelper().getGroupCount();
    List<GroupData> before= app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size()-1);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returntoGroupPage();
//    int after = app.getGroupHelper().getGroupCount();
    List<GroupData> after= app.getGroupHelper().getGroupList();
//    Assert.assertEquals(after, before-1);
    Assert.assertEquals(after.size(), before.size()-1);

    before.remove(before.size()-1);
    Assert.assertEquals(before, after
    );

  }

}
