package ru.stqa.pdt.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.SkipException;

import java.io.IOException;
import java.util.Set;

public class TestBase {


  public Executor getExecutor() {
    return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
  }

  public String getIssueStatusById(int issueId) throws IOException {
    String json = getExecutor().execute(Request
            .Get("http://bugify.stqa.ru/api/issues/" + issueId + ".json")).returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    Set<Issue> issue = new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
    }.getType());
    String status = issue.iterator().next().getStatus();
    return status;
  }

  public boolean isIssueOpen(int issueId) throws IOException {
    String status = getIssueStatusById(issueId);
    boolean isIssueOpen = false;
    if (status.equals("Open") | status.equals("Re-opened") | status.equals("In progress")) {
      isIssueOpen = true;
    }
    return isIssueOpen;
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

}
