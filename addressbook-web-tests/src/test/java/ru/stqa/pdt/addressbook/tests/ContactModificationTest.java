package ru.stqa.pdt.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pdt.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Ann", "Chasovskikh",
              "ann_chasovskikh", "Novosibirsk", "+78548960052", "annChas@gmail.com", "test1"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Ann", "Chasovskikh", "ann_chasovskikh", "Novosibirsk",
            "+78548960052", "annChas@gmail.com", null), false);
    app.getContactHelper().submitContactModification();
  }
}
