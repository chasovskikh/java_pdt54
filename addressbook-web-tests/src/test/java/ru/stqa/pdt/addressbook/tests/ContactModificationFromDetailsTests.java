package ru.stqa.pdt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pdt.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationFromDetailsTests extends TestBase {

  @Test
  public void testContactModificationFromDetails(){
    app.getNavigationHelper().gotoHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Ann", "Chasovskikh",
              "ann_chasovskikh", "Novosibirsk", "+78548960052", "annChas@gmail.com", "test1"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().initDetailsSelectionContact();
    app.getContactHelper().initModifyContact();
    ContactData contact = new ContactData("Anna", "Chasovskikh", "anna_chasovskikh", "Novosibirsk",
            "+78548960052", "annChas@gmail.com", null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
