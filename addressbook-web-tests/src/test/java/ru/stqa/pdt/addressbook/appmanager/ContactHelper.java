package ru.stqa.pdt.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pdt.addressbook.model.ContactData;
import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("email"), contactData.getEmail());

//    if (creation) {
//      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
//    } else {
//      Assert.assertFalse(isElementPresent(By.name("new_group")));
//    }

  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void deleteSelectionContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void submitDeleteContact() {
    wd.switchTo().alert().accept();
  }

  public void initContactModification(int index) {
      wd.findElements(By.cssSelector("img[title='Edit']")).get(index).click();
}

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void initDetailsSelectionContact() {
    click(By.xpath("div/div[4]/form[2]/table/tbody/tr[2]/td[7]/a/img"));
  }

  public void initModifyContact() {
    click(By.name("modifiy"));
  }

  public void createContact(ContactData contact) {
    initContactCreation();
    fillContactForm(new ContactData("Ann", "Chasovskikh",
            "ann_chasovskikh", "Novosibirsk", "+78548960052", "annChas@gmail.com", "test1"), true);
    submitContactCreation();
    returnToHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> content = element.findElements(By.tagName("td"));
      String lastname = content.get(1).getText();
      String firstname = content.get(2).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData(id, firstname, lastname, null, null, null, null, null);
      contacts.add(contact);
    }
    return contacts;

  }
}
