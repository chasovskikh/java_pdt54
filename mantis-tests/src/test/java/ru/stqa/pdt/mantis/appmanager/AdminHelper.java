package ru.stqa.pdt.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.pdt.mantis.model.UserData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AdminHelper extends HelperBase {

  public AdminHelper(ApplicationManager app) {
    super(app);
  }

  public void goToManagePanel() {
    click(By.linkText("Manage"));
  }

  public void goToManageUser() {
    click(By.linkText("Manage Users"));
  }

  public Set<UserData> all() {
    Set<UserData> users = new HashSet<>();
    List<WebElement> elements = wd.findElements(By.className("row-1"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String name = cells.get(0).getText();
      String email = cells.get(2).getText();
      users.add(new UserData().withName(name).withEmail(email));
    }
    return users;
  }

  public void selectUser(String username) {
    click(By.linkText(username));
  }

  public void resetPassword() {
    click(By.cssSelector("input[value='Reset Password'"));
  }


}
