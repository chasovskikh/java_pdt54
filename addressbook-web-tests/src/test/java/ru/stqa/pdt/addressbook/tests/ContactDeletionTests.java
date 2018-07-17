package ru.stqa.pdt.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectionContact();
    app.getContactHelper().submitDeleteContact();
    app.getNavigationHelper().gotoHomePage();
  }

}
