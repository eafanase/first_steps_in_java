package ru.stqa.katja.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.katja.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
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
    app.getGroupHelper().initGroupModification(); // создали переменную чтобы использовать ее в 32 строке before.add(group), чтоб не прописывать все атрибуты группы
    GroupData group = new GroupData(before.get(before.size()-1).getId(),"test1", "TEST2", "TEST3");
    app.getGroupHelper().fillGroupForm(group); // вместо всех перечисленных атрибутов группы вставили переменную, созданную выше
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returntoGroupPage();
//    int after = app.getGroupHelper().getGroupCount();
    List<GroupData> after= app.getGroupHelper().getGroupList();
//    Assert.assertEquals(after, before);
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size()-1);
    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId()); //добавили компаратор
    before.sort(byId);        //сортировка первого списка
    after.sort(byId);         //сортировка второго списка
//    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after)); //теперь можно просто сравнивать списки
    Assert.assertEquals(before, after);
  }
}
