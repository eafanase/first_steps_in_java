package ru.stqa.katja.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.katja.addressbook.model.GroupData;

public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void returntoGroupPage(String group_page) {
    click(By.linkText("group page"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void initGroupCreation(String s) {
    click(By.name(s));
  }

  public void deleteSelectedGroups() {
    click(By.xpath("(//input[@name='delete'])[2]"));
  }

  public void selectGroup() {
    click(By.xpath("(//input[@name='selected[]'])[2]"));
  }

  public void submitGroupCreation(String submit) {
    click(By.name(submit));
  }

}
