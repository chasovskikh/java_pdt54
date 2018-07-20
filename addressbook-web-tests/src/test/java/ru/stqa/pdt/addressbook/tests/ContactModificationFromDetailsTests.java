package ru.stqa.pdt.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pdt.addressbook.model.ContactData;

public class ContactModificationFromDetailsTests extends TestBase {

  @Test
  public void testContactModificationFromDetails(){

    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().initDetailsSelectionContact();
    app.getContactHelper().initModifyContact();
    app.getContactHelper().fillContactForm(new ContactData("Ann", "Chasovskikh", "ann_chasovskikh",
            "Novosibirsk", "+78548960052", "annChas@gmail.com", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
  }
}
