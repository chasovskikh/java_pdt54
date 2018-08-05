package ru.stqa.pdt.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pdt.addressbook.model.ContactData;
import ru.stqa.pdt.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationFromDetailsTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("Ann").withLastname("Chasovskikh").withNickname("ann_chasovskikh")
              .withAddress("Novosibirsk").withMobile("+78548960052").withEmail("annChas@gmail.com").withGroup("test1"));
    }
  }

  @Test
  public void testContactModificationFromDetails() {
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("Ann").withLastname("Chasovskikh").withNickname("ann_chasovskikh")
            .withAddress("Novosibirsk").withMobile("+78548960052").withEmail("annChas@gmail.com").withGroup("test1");

    app.contact().modifyFromDetails(contact);
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }


}
