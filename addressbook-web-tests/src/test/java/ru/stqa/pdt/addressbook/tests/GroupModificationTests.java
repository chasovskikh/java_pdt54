package ru.stqa.pdt.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pdt.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModofocation();
    app.getGroupHelper().fillGroupForm(new GroupData("test1-2", "test2-2", "test3-2"));
    app.getGroupHelper().submitGroupModofication();
    app.getGroupHelper().returnToGroupPage();

  }
}
