package ru.stqa.katja.addressbook.generators;

import ru.stqa.katja.addressbook.model.ContactData;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
  public static void main (String [] args) throws IOException {
    int count = Integer.parseInt(args[0]);
    File file = new File (args[1]);

    List <ContactData> contacts = generateContacts(count);
    save(contacts, file);
  }

  private static void save(List<ContactData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (ContactData contact: contacts) {
      writer.write(String.format("%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname(), contact.getPhone(), contact.getGroup()));
    }
    writer.close();
  }

  private static List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData().withFirstname(String.format("Donald %s", i))
              .withLastname(String.format("Duck %s", i)).withPhone(String.format("88888%s", i)).withGroup(String.format("test1", i)));
    }
    return contacts;
  }
}
