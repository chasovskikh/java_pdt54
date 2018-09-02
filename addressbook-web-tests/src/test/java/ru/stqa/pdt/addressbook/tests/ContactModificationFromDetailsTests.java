package ru.stqa.pdt.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pdt.addressbook.model.ContactData;
import ru.stqa.pdt.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationFromDetailsTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData()
              .withFirstname("Ann").withLastname("Chasovskikh").withNickname("ann_chasovskikh")
              .withAddress("Novosibirsk").withHomePhone("123456").withMobilePhone("+78548960052").withWorkPhone("123454321")
//              .withGroup("test1")
      );
    }
  }

  @Test
  public void testContactModificationFromDetails() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("Ann").withMiddlename("test m").withLastname("Chasovskikh").withNickname("ann_chasovskikh")
            .withCompany("company test").withTitle("Title text").withAddress("Novosibirsk").withHomePhone("1234567890").withMobilePhone("+78548960052").withWorkPhone("0987654321")
            .withFax("78548745").withEmail("annChas@gmail.com").withEmail2("test@gmail.com").withEmail3("test1@gmail.com");
    app.goTo().homePage();
    app.contact().modifyFromDetails(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    verifyContactListInUI();
  }


}
