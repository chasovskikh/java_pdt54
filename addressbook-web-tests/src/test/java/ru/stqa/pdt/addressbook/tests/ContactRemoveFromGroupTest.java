package ru.stqa.pdt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pdt.addressbook.model.ContactData;
import ru.stqa.pdt.addressbook.model.Contacts;
import ru.stqa.pdt.addressbook.model.GroupData;
import ru.stqa.pdt.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemoveFromGroupTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("testModify"));
    }
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData()
              .withFirstname("Ann").withLastname("Chasovskikh").withNickname("ann_chasovskikh")
              .withAddress("Novosibirsk").withHomePhone("1234567890").withMobilePhone("+78548960052").withWorkPhone("0987654321")
              .withEmail("annChas@gmail.com"));
    }

  }

  @Test
  public void testContactRemoveFromGroup() {
    Groups groups = app.db().groups();
    GroupData group = groups.iterator().next();
    app.goTo().homePage();
    app.contact().selectGroupsForFilter(group);
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("QWERTY").withLastname("Chasovskikh").withNickname("qwerty")
              .withAddress("Novosibirsk").withMobilePhone("+78548960052")
              .withEmail("annChas@gmail.com").inGroup(groups.iterator().next()));
      app.goTo().homePage();
      app.contact().selectGroupsForFilter(group);
    }
    Contacts contacts = app.contact().all();
    ContactData contact = contacts.iterator().next();
    app.contact().removeFromGroup(contact, group);
    assertThat(contact.getGroups().size(), equalTo(0));
  }
}
