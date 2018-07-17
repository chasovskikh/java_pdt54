package ru.stqa.pdt.addressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.pdt.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("Ann", "Chasovskikh", "ann_chasovskikh", "Novosibirsk", "+78548960052", "annChas@gmail.com"));
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().returnToHomePage();
    }

}
