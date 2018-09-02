package ru.stqa.pdt.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pdt.addressbook.model.ContactData;
import ru.stqa.pdt.addressbook.model.GroupData;
import ru.stqa.pdt.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData()
              .withFirstname("Ann").withLastname("Chasovskikh").withNickname("ann_chasovskikh")
              .withAddress("Novosibirsk").withHomePhone("1234567890").withMobilePhone("+78548960052").withWorkPhone("0987654321")
              .withEmail("annChas@gmail.com"));
    }
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("testModify"));
    }
  }

  @Test
  public void testContactAddToGroup() {
    Groups groups = app.db().groups();
    GroupData group = groups.iterator().next();
    ContactData contact = app.db().contacts().iterator().next();
    app.goTo().homePage();
    app.contact().addToGroup(contact, group);
    app.db().refresh(contact);
    assertThat(contact.getGroups(), hasItem(group));
  }
}
