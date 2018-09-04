package ru.stqa.pdt.mantis.appmanager;

import org.openqa.selenium.By;
public class LoginHelper extends HelperBase{
  public LoginHelper(ApplicationManager app) {
    super(app);
  }

  public void byUser(String userName, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), userName);
    click(By.cssSelector("input[type='submit']"));
    type(By.name("password"), password);
    click(By.cssSelector("input[type='submit']"));

  }
}
