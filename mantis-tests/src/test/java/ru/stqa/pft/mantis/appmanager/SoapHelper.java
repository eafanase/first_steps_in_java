package ru.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import org.apache.axis.SimpleChain;
import org.apache.axis.SimpleTargetedChain;
import org.apache.axis.client.AxisClient;
import org.apache.axis.configuration.SimpleProvider;
import org.apache.axis.transport.http.HTTPSender;
import org.apache.axis.transport.http.HTTPTransport;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.axis.Handler;

public class SoapHelper {
  private ApplicationManager app;

  public SoapHelper(ApplicationManager app){
    this.app = app;
  }
  public Set<Project> getProjects() throws RemoteException, MalformedURLException, ServiceException {
    MantisConnectPortType mc = getMantisConnect();
    ProjectData[] projects = mc.mc_projects_get_user_accessible(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    return Arrays.asList(projects).stream()
            .map((p)-> new Project().withId(p.getId().intValue()).withName(p.getName())).collect(Collectors.toSet());
  }

  private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
    SimpleProvider clientConfig = new SimpleProvider();
    AxisLogHandler logHandler = new AxisLogHandler();
    SimpleChain reqHandler = new SimpleChain();
    SimpleChain respHandler = new SimpleChain();
    reqHandler.addHandler(logHandler);
    respHandler.addHandler(logHandler);
    Handler pivot = new HTTPSender();
    Handler transport = new SimpleTargetedChain(reqHandler, pivot, respHandler);
    clientConfig.deployTransport(HTTPTransport.DEFAULT_TRANSPORT_NAME, transport);

    MantisConnectLocator locator = new MantisConnectLocator();
    locator.setEngineConfiguration(clientConfig);
    locator.setEngine(new AxisClient(clientConfig));
    return locator.getMantisConnectPort(new URL("http://localhost:8080/mantisbt-2.24.2/mantisbt-2.24.2/api/soap/mantisconnect.php?wsdl"));
  }


//  private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
//    return new MantisConnectLocator()
//              .getMantisConnectPort(new URL("http://localhost:8080/mantisbt-2.24.2/mantisbt-2.24.2/api/soap/mantisconnect.php?wsdl"));
//  }

  public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    String [] categories = mc.mc_project_get_categories(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), BigInteger.valueOf(issue.getProject().getId()));
    IssueData issueData = new IssueData();
    issueData.setSummary(issue.getSummary());
    issueData.setDescription(issue.getDescription());
    issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
    issueData.setCategory(categories[0]);
    BigInteger issueId = mc.mc_issue_add(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), issueData);
    IssueData createdIissueData = mc.mc_issue_get(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), issueId);
    return new Issue().withId(createdIissueData.getId().intValue())
            .withSummary(createdIissueData.getSummary())
            .withDescription(createdIissueData.getDescription())
            .withProject(new Project().withId(createdIissueData.getProject().getId().intValue())
                                      .withName(createdIissueData.getProject().getName()));
  }


  public IssueData getIssue(int issueId) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    return mc.mc_issue_get(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), BigInteger.valueOf(issueId));

  }




}
