package ru.stqa.pft.mantis.tests;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import java.io.File;
import java.io.IOException;


public class TestBase {


  protected static ApplicationManager app;

  static {
    try {
      app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @BeforeSuite
  public void setUp() throws IOException {
    app.init();
    app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php_back");
  }

  @AfterSuite (alwaysRun = true)
  public void tearDown() throws IOException {
    app.ftp().restore("config_inc.php_back", "config_inc.php");
    app.stop();
  }




}
