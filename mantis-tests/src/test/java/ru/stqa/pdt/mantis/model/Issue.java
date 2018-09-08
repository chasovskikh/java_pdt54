package ru.stqa.pdt.mantis.model;

public class Issue {

  private int id;
  private String sumary;
  private String description;
  private Project project;

  public int getId() {
    return id;
  }

  public Issue withId(int id) {
    this.id = id;
    return this;
  }

  public String getSumary() {
    return sumary;
  }

  public Issue withSumary(String sumary) {
    this.sumary = sumary;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public Issue withDescription(String description) {
    this.description = description;
    return this;
  }

  public Project getProject() {
    return project;
  }

  public Issue withProject(Project project) {
    this.project = project;
    return this;
  }
}
