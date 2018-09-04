package ru.stqa.pdt.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pdt.mantis.model.MailMessage;
import ru.stqa.pdt.mantis.model.UserData;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase {
  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testResetPassword() throws IOException, MessagingException {
    app.login().byUser(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    app.admin().goToManagePanel();
    app.admin().goToManageUser();
    Set<UserData> allUsers = app.admin().all();
    UserData user = allUsers.iterator().next();
    app.admin().selectUser(user.getName());
    app.admin().resetPassword();
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, user.getEmail());
    String password = "testpass";
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user.getName(), password));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}