package ru.stqa.katja.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.katja.addressbook.model.GroupData;

import java.util.List;

public class GroupModificationTest extends TestBase {

  @Test
  public void testGroupModification() {
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", "TEST2", "TEST3"));
    }
//    int before = app.getGroupHelper().getGroupCount();
    List<GroupData> before= app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size()-1);
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test1", "TEST2", "TEST3"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returntoGroupPage();
//    int after = app.getGroupHelper().getGroupCount();
    List<GroupData> after= app.getGroupHelper().getGroupList();
//    Assert.assertEquals(after, before);
    Assert.assertEquals(after.size(), before.size());
  }
}
