package ru.stqa.katja.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.katja.addressbook.model.GroupData;

public class GroupModificationTest extends TestBase {

  @Test
  public void testGroupModification() {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test1", "TEST2", "TEST3"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returntoGroupPage();
  }
}
