package ru.stqa.katja.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.katja.addressbook.model.GroupData;

public class GroupHelper {
  private WebDriver wd;

  public GroupHelper(WebDriver wd) {
    this.wd=wd;
  }

  public void returntoGroupPage(String group_page) {
    wd.findElement(By.linkText("group page")).click();
  }

  public void fillGroupForm(GroupData groupData) {
    wd.findElement(By.name("group_name")).click();
    wd.findElement(By.name("group_name")).clear();
    wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
    wd.findElement(By.name("group_header")).click();
    wd.findElement(By.name("group_header")).clear();
    wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
    wd.findElement(By.name("group_footer")).click();
    wd.findElement(By.name("group_footer")).clear();
    wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
  }

  public void initGroupCreation(String s) {
    wd.findElement(By.name(s)).click();
  }

  public void deleteSelectedGroups() {
    wd.findElement(By.xpath("(//input[@name='delete'])[2]")).click();
  }

  public void selectGroup() {
    wd.findElement(By.xpath("(//input[@name='selected[]'])[2]")).click();
  }

  public void submitGroupCreation(String submit) {
    wd.findElement(By.name(submit)).click();
  }
}
