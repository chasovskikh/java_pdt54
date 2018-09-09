package ru.stqa.pdt.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pdt.mantis.model.Issue;
import ru.stqa.pdt.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase {

  @Test
  public void testGetProjects() throws MalformedURLException, RemoteException, ServiceException {
    skipIfNotFixed(8);
    String adminL = app.getProperty("web.adminLogin");
    String adminP = app.getProperty("web.adminPassword");
    String uRL = app.getProperty("web.mantisconnect");
    Set<Project> projects = app.soap().getProjects(adminL, adminP, uRL);
    System.out.println(projects.size());
    for (Project project : projects) {
      System.out.println(project.getName());
    }
  }

  @Test
  public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
    String adminL = app.getProperty("web.adminLogin");
    String adminP = app.getProperty("web.adminPassword");
    String uRL = app.getProperty("web.mantisconnect");
    Set<Project> projects = app.soap().getProjects(adminL, adminP, uRL);
    Issue issue = new Issue().withSumary("Test issue")
            .withDescription("Test issue description").withProject(projects.iterator().next());
    Issue created = app.soap().addIssue(issue, adminL, adminP, uRL);
    assertEquals(issue.getSumary(), created.getSumary());
  }

}
