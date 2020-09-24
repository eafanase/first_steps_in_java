package ru.stqa.katja.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.katja.addressbook.model.ContactData;
import ru.stqa.katja.addressbook.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
      app.goTo().homePage();
      if (app.contact().list().size() == 0) {
        app.contact().create(new ContactData().withFirstname("Donald").withLastname("Duck").withPhone("111").withMobphone("222").withWorkphone("333"));
        app.goTo().homePage();
      }
    }

    @Test
    public void testContactPhones(){
      app.goTo().homePage();
      ContactData contact = app.contact().all().iterator().next();
      ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
      assertThat(contact.getPhone(), equalTo(cleaned(contactInfoFromEditForm.getPhone())));
      assertThat(contact.getMobphone(), equalTo(cleaned(contactInfoFromEditForm.getMobphone())));
      assertThat(contact.getWorkphone(), equalTo(cleaned(contactInfoFromEditForm.getWorkphone())));
    }
    public String cleaned(String phone){
      return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }
  }

