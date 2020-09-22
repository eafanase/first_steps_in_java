package ru.stqa.katja.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.katja.addressbook.model.GroupData;
import ru.stqa.katja.addressbook.model.Groups;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("test2");
    app.group().create(group);
    Groups after = app.group().all();
//    Assert.assertEquals(after.size(), before.size() + 1);
    assertThat(after.size(), equalTo(before.size() + 1));

 //  group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
 //   before.add(group);
//    Assert.assertEquals(before, after);
    assertThat(after, equalTo
            (before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));

//    app.sessionHelper.logout();
  }

}
