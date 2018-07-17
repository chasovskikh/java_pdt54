package ru.stqa.pdt.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pdt.addressbook.model.ContactData;

public class ContactHelper extends HelperBase{

  public ContactHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
      click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData) {
      type(By.name("firstname"),contactData.getFirstname());
      type(By.name("lastname"),contactData.getLastname());
      type(By.name("nickname"),contactData.getNickname());
      type(By.name("address"),contactData.getAddress());
      type(By.name("mobile"),contactData.getMobile());
      type(By.name("email"),contactData.getEmail());
  }

  public void initContactCreation() {
      click(By.linkText("add new"));
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void deleteSelectionContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void submitDeleteContact() {
    wd.switchTo().alert().accept();
  }

  public void initContactModification() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void initDetailsSelectionContact() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[7]/a/img"));
  }

  public void initModifyContact() {
    click(By.name("modifiy"));
  }
}
