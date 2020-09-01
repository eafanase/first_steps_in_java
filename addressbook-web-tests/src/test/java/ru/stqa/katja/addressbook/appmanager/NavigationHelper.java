package ru.stqa.katja.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void gotoGroupPage(String groups) {
    click(By.linkText(groups));

  }

  public void gotoHomePage(String home) {
    click(By.linkText(home));

  }

  public void closeAlert() {
    wd.switchTo().alert().accept();
  }
}
