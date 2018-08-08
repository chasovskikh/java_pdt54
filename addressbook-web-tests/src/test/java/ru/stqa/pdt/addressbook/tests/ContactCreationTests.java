package ru.stqa.pdt.addressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.pdt.addressbook.model.ContactData;
import ru.stqa.pdt.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstname("Anny").withLastname("Chasovskikh").withNickname("ann_chasovskikh")
            .withAddress("Novosibirsk").withHomePhone("123456").withMobilePhone("+78548960052").withWorkPhone("123454321").withEmail("annChas@gmail.com");
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(
            contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

  @Test
  public void testBadContactCreation() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstname("Anny'").withLastname("Chasovskikh").withNickname("ann_chasovskikh")
            .withAddress("Novosibirsk").withHomePhone("123456").withMobilePhone("+78548960052").withWorkPhone("123454321").withEmail("annChas@gmail.com");
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }


}
