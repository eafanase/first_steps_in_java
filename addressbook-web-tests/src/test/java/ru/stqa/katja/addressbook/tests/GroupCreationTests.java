package ru.stqa.katja.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.katja.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before= app.getGroupHelper().getGroupList();
//    int before = app.getGroupHelper().getGroupCount(); //переменная теперь содержит список элементов, а не их колличество
    app.getGroupHelper().initGroupCreation();
    GroupData group = new GroupData("test1", "null", "null");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returntoGroupPage();
//    int after = app.getGroupHelper().getGroupCount();//
    List<GroupData> after= app.getGroupHelper().getGroupList(); //переменная теперь содержит список элементов после, а не их колличество
//    Assert.assertEquals(after, before+1); // проверка тоже меняется
    Assert.assertEquals(after.size(), before.size()+1);


    int max =0;
    for (GroupData g: after){
      if (g.getId()> max) {
        max = g.getId();
          }
    }
    group.setId(max);
    before.add(group);
    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));


    app.sessionHelper.logout();


  }

}
