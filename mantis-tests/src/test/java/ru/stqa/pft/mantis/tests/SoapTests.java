package ru.stqa.pft.mantis.tests;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.apache.axis.SimpleChain;
import org.apache.axis.configuration.SimpleProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.AxisLogHandler;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase {



  @Test
  public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
    Set<Project> projects = app.soap().getProjects();
    System.out.println(projects.size());
    for (Project project: projects){
      System.out.println(project.getName());
    }

  }

  @Test
  public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
    Set<Project> projects = app.soap().getProjects();
    Issue issue =new Issue()
            .withSummary("Test issue")
            .withDescription("Test issue description")
            .withProject(projects.iterator().next());

    Issue created = app.soap().addIssue(issue);
    assertEquals(issue.getSummary(), created.getSummary());
  }

  @Test
  public void testSkip() throws IOException, ServiceException {
    int blockedIssueId  = 3;
    skipIfNotFixed(blockedIssueId );
    System.out.println("solved");
  }

}
