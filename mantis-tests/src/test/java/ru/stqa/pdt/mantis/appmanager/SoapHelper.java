package ru.stqa.pdt.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stqa.pdt.mantis.model.Issue;
import ru.stqa.pdt.mantis.model.Project;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {

  private ApplicationManager app;

  public SoapHelper(ApplicationManager app) {
    this.app = app;
  }

  public Set<Project> getProjects(String adminL, String adminP, String uRL) throws MalformedURLException, RemoteException, javax.xml.rpc.ServiceException {
    MantisConnectPortType mc = getMantisConnect(uRL);
    ProjectData[] projects = mc.mc_projects_get_user_accessible(adminL,adminP);
    return Arrays.asList(projects).stream()
            .map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName()))
            .collect(Collectors.toSet());
  }

  private MantisConnectPortType getMantisConnect(String uRL) throws javax.xml.rpc.ServiceException, MalformedURLException {
    return new MantisConnectLocator()
            .getMantisConnectPort(new URL(uRL));
  }

  public Issue addIssue(Issue issue, String adminL, String adminP, String uRL) throws MalformedURLException, javax.xml.rpc.ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect(uRL);
    String[] categories = mc.mc_project_get_categories(adminL,adminP, BigInteger.valueOf(issue.getProject().getId()));
    IssueData issueData = new IssueData();
    issueData.setSummary(issue.getSumary());
    issueData.setDescription(issue.getDescription());
    issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
    issueData.setCategory(categories[0]);
    BigInteger issueId = mc.mc_issue_add(adminL, adminP, issueData);
    IssueData createIssueData = mc.mc_issue_get(adminL,adminP, issueId);
    return new Issue().withId(createIssueData.getId().intValue())
            .withSumary(createIssueData.getSummary())
            .withDescription(createIssueData.getDescription())
            .withProject(new Project().withId(createIssueData.getProject().getId().intValue())
                                      .withName(createIssueData.getProject().getName()));
  }

  public String getIssue(int issueId, String adminL, String adminP, String uRL) throws MalformedURLException, javax.xml.rpc.ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect(uRL);
    IssueData issueData = mc.mc_issue_get(adminL,adminP, BigInteger.valueOf(issueId));
    ObjectRef status = issueData.getStatus();
    return status.getName();
  }
}
