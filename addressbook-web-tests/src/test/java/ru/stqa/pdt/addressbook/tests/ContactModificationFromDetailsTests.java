package ru.stqa.pdt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pdt.addressbook.model.ContactData;

public class ContactModificationFromDetailsTests extends TestBase {

  @Test
  public void testContactModificationFromDetails(){
    app.getNavigationHelper().gotoHomePage();
    int before = app.getContactHelper().getContactCount();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Ann", "Chasovskikh",
              "ann_chasovskikh", "Novosibirsk", "+78548960052", "annChas@gmail.com", "test1"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().initDetailsSelectionContact();
    app.getContactHelper().initModifyContact();
    app.getContactHelper().fillContactForm(new ContactData("Anna", "Chasovskikh", "anna_chasovskikh",
            "Novosibirsk", "+78548960052", "annaChas@gmail.com", null), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before);
  }
}
