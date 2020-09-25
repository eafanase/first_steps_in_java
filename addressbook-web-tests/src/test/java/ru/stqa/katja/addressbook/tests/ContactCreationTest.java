package ru.stqa.katja.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.katja.addressbook.model.Contact;
import ru.stqa.katja.addressbook.model.ContactData;
import java.io.File;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
public class ContactCreationTest extends TestBase {


  @Test
  public void testContactCreation() {
    Contact before = app.contact().all();
    File photo = new File("src/test/resources/avatar.png");
    ContactData contact = new ContactData().withFirstname("Donald").withLastname("Duck").withPhoto(photo).withGroup("test1");
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
