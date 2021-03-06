package ru.stqa.pdt.addressbook.appmanager;

import org.hibernate.Session;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pdt.addressbook.model.ContactData;
import ru.stqa.pdt.addressbook.model.Contacts;
import ru.stqa.pdt.addressbook.model.GroupData;

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
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("company"), contactData.getCompany());
    type(By.name("title"), contactData.getTitle());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"),contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"),contactData.getWorkPhone());
    type(By.name("fax"),contactData.getFax());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
//    attach(By.name("photo"), contactData.getPhoto());

    if (creation) {
      if (contactData.getGroups().size() > 0 ) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void initGoToGroupPage(GroupData group) {
    wd.findElement(By.cssSelector("a[href='./?group=" + group.getId() + "']")).click();
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void deleteSelectionContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void submitDeleteContact() {
    wd.switchTo().alert().accept();
  }

  public void initContactModification(int id) {
    wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void initDetailsSelectionContact(int id) {
    wd.findElement(By.cssSelector("a[href='view.php?id=" + id + "']")).click();
  }

  public void initModifyContact() {
    click(By.name("modifiy"));
  }

  public void initAddToGroupContact() {
    wd.findElement(By.name("add")).click();
  }

  public void initRemoveFromGroupContact() {
    wd.findElement(By.name("remove")).click();
  }

  public void selectGroupsForContact(GroupData group) {
    new Select(wd.findElement(By.name("to_group"))).selectByValue(Integer.toString(group.getId()));
  }

  public void selectGroupsForFilter(GroupData group) {
    click(By.xpath("//select[@name='group']//option[@value='" + group.getId() + "']"));
  }

  public void create(ContactData contact) {
    initContactCreation();
    fillContactForm(contact, true);
    submitContactCreation();
    contactCache = null;
    returnToHomePage();
  }

  public void modifyContactsAll(ContactData contact) {
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    initContactModification(contact.getId());
    modifyContactsAll(contact);
  }

  public void addToGroup(ContactData contact, GroupData group) {
    selectContactById(contact.getId());
    selectGroupsForContact(group);
    initAddToGroupContact();
    initGoToGroupPage(group);
  }

  public void removeFromGroup(ContactData contact, GroupData group) {
    selectContactById(contact.getId());
    initRemoveFromGroupContact();
    initGoToGroupPage(group);
  }



  public void modifyFromDetails(ContactData contact) {
    initDetailsSelectionContact(contact.getId());
    initModifyContact();
    modifyContactsAll(contact);
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModification(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String middlename = wd.findElement(By.name("middlename")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String nickname = wd.findElement(By.name("nickname")).getAttribute("value");
    String company = wd.findElement(By.name("company")).getAttribute("value");
    String title = wd.findElement(By.name("title")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String homePhone = wd.findElement(By.name("home")).getAttribute("value");
    String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
    String workPhone = wd.findElement(By.name("work")).getAttribute("value");
    String fax = wd.findElement(By.name("fax")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withMiddlename(middlename).withLastname(lastname).withNickname(nickname)
            .withCompany(company).withTitle(title).withAddress(address)
            .withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone).withFax(fax)
            .withEmail(email).withEmail2(email2).withEmail3(email3);
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectionContact();
    submitDeleteContact();
    contactCache = null;
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String address = cells.get(3).getText();
      String allEmail = cells.get(4).getText();
      String allPhones = cells.get(5).getText();
      contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAddress(address).withAllPhones(allPhones).withAllEmail(allEmail));
    }
    return new Contacts(contactCache);

  }

}
