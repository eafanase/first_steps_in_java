package ru.stqa.katja.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.katja.addressbook.model.Contact;
import ru.stqa.katja.addressbook.model.ContactData;
import ru.stqa.katja.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
public class ContactCreationTest extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    List<Object[]> list= new ArrayList<Object[]>();
//    File photo = new File("src/test/resources/avatar.png");
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
    String line = reader.readLine();
    while (line != null){
      String[] split= line.split(";");
      list.add(new Object[] {new ContactData().withFirstname(split[0]).withLastname(split[1]).withPhone(split[2]).withGroup(split[3])});
      line = reader.readLine();
    }
    return list.iterator();
  }



  @Test(dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) {
    Contact before = app.contact().all();
//    File photo = new File("src/test/resources/avatar.png");
//    ContactData contact = new ContactData().withFirstname("Donald").withLastname("Duck").withPhoto(photo).withGroup("test1");
    app.contact().create(contact);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contact after = app.contact().all();
    assertThat(after, equalTo
            (before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    //   app.sessionHelper.logout();
  }

  @Test (enabled = false)
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/avatar.png");
    System.out.println(currentDir.getAbsolutePath());
    System.out.println(photo.exists());
  }
}
