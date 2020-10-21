package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import org.testng.SkipException;

import java.util.Set;

public class TestBase {


  public boolean isIssueOpen(int issueId) {
    RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
    String json = RestAssured.get("https://bugify.stqa.ru/api/issues/" + issueId + ".json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issuesAsJson = parsed.getAsJsonObject().get("issues");
    Set<Issue> issues = new Gson().fromJson(issuesAsJson, new TypeToken<Set<Issue>>() {
    }.getType());
    Issue issue = issues.iterator().next();
    if (issue.getState_name().equals("Resolved") || issue.getState_name().equals("Closed")) {
      return false;
    }
    return true;
  }


  public void skipIfNotFixed(int issueId) {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

}
