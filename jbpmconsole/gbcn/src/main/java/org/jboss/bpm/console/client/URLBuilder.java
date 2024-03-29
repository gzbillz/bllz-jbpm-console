package org.jboss.bpm.console.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.URL;
import java.util.Date;
import java.util.List;
import org.jboss.bpm.console.client.model.ProcessInstanceRef;
import org.jboss.bpm.console.client.model.TokenReference;

public class URLBuilder
{
  private static ConsoleConfig config;
  private static URLBuilder instance = null;

  public static void configureInstance(ConsoleConfig config)
  {
    instance = new URLBuilder(config);
  }

  public static URLBuilder getInstance()
  {
    if (null == instance) {
      throw new IllegalArgumentException("UrlBuilder not configured");
    }
    return instance;
  }

  @SuppressWarnings("static-access")
private URLBuilder(ConsoleConfig config)
  {
    this.config = config;
  }

  public String getProcessDefinitionsURL()
  {
    return config.getConsoleServerUrl() + "/rs/process/definitions";
  }

  public String getProcessInstancesURL(String processId)
  {
    String encodedId = URL.encode(processId);
    System.out.println("the decoded Id is: " + processId);
    System.out.println("the encoded Id is: " + encodedId);
    return config.getConsoleServerUrl() + "/rs/process/definition/" + encodedId + "/instances";
  }

  public String getUserInRoleURL(String[] possibleRoles)
  {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < possibleRoles.length; i++)
    {
      sb.append(URL.encode(possibleRoles[i]));
      if (i < possibleRoles.length - 1)
        sb.append(",");
    }
    return config.getConsoleServerUrl() + "/rs/identity/user/roles?roleCheck=" + sb.toString();
  }

  public String getServerStatusURL()
  {
    return config.getConsoleServerUrl() + "/rs/server/status";
  }

  @Deprecated
  public String getRemoveDefinitionURL(String processId)
  {
    String encodedId = URL.encode(processId);
    return config.getConsoleServerUrl() + "/rs/process/definition/" + encodedId + "/remove";
  }

  public String getProcessImageURL(String processId)
  {
    String encodedId = URL.encode(processId);
    return config.getConsoleServerUrl() + "/rs/process/definition/" + encodedId + "/image";
  }

  public String getActiveNodeInfoURL(String instanceId)
  {
    return config.getConsoleServerUrl() + "/rs/process/instance/" + instanceId + "/activeNodeInfo";
  }

  public String getActivityImage(String processId, String instanceId)
  {
    String encodedId = URL.encode(processId);

    String versionNo = String.valueOf(new Date().getTime());
    return config.getConsoleServerUrl() + "/rs/process/definition/" + encodedId + "/image/" + instanceId + "?v=" + versionNo;
  }

  public String getStateChangeURL(String instanceId, ProcessInstanceRef.STATE state)
  {
    return config.getConsoleServerUrl() + "/rs/process/instance/" + instanceId + "/state/" + state;
  }

  public String getInstanceEndURL(String instanceId, ProcessInstanceRef.RESULT result)
  {
    return config.getConsoleServerUrl() + "/rs/process/instance/" + instanceId + "/end/" + result;
  }

  public String getInstanceDeleteURL(String instanceId)
  {
    return config.getConsoleServerUrl() + "/rs/process/instance/" + instanceId + "/delete";
  }

  public String getInstanceDataURL(String instanceId)
  {
    return config.getConsoleServerUrl() + "/rs/process/instance/" + instanceId + "/dataset";
  }

  public String getStartNewInstanceURL(String processId)
  {
    String encodedID = URL.encode(processId);
    return config.getConsoleServerUrl() + "/rs/process/definition/" + encodedID + "/new_instance";
  }

  public String getTaskListURL(String idRef)
  {
    return config.getConsoleServerUrl() + "/rs/tasks/" + URL.encode(idRef);
  }

  public String getParticipationTaskListURL(String idRef)
  {
    return config.getConsoleServerUrl() + "/rs/tasks/" + URL.encode(idRef) + "/participation";
  }

  public String getTaskReleaseURL(long id)
  {
    return config.getConsoleServerUrl() + "/rs/task/" + id + "/release";
  }

  public String getTaskCompleteURL(long id)
  {
    return config.getConsoleServerUrl() + "/rs/task/" + id + "/close";
  }

  public String getTaskCompleteURL(long id, String outcome)
  {
    return config.getConsoleServerUrl() + "/rs/task/" + id + "/close/" + URL.encode(outcome);
  }

  public String getTaskAssignURL(long id, String idRef)
  {
    return config.getConsoleServerUrl() + "/rs/task/" + id + "/assign/" + URL.encode(idRef);
  }

  public String getExecutionSignalUrl(TokenReference tok)
  {
    return config.getConsoleServerUrl() + "/rs/process/tokens/" + tok.getId() + "/transition/default";
  }

  public String getExecutionSignalUrl(TokenReference tok, String signal)
  {
    return config.getConsoleServerUrl() + "/rs/process/tokens/" + tok.getId() + "/transition?signal=" + signal;
  }

  public String getAvailableActorsUrl(String actorId)
  {
    return config.getConsoleServerUrl() + "/rs/identity/user/" + actorId + "/actors";
  }

  public String getDeployTestHarnessUrl()
  {
    return config.getConsoleServerUrl() + "/rs/test/deploy/harness";
  }

  public String getUndeployTestHarnessUrl()
  {
    return config.getConsoleServerUrl() + "/rs/test/undeploy/harness";
  }

  public String getReportURL(String templateName)
  {
    String reportFileName = URL.encode(templateName);
    String url = config.getConsoleServerUrl() + "/rs/report/render/" + reportFileName;
    if (!GWT.isScript())
      url = url + "?id=birt";
    return url;
  }

  public String getDeploymentsUrl()
  {
    return config.getConsoleServerUrl() + "/rs/engine/deployments";
  }

  public String getSuspendDeploymentUrl(String id)
  {
    String encodedId = URL.encode(id);
    return config.getConsoleServerUrl() + "/rs/engine/deployment/" + encodedId + "/suspend";
  }

  public String getResumeDeploymentUrl(String id)
  {
    String encodedId = URL.encode(id);
    return config.getConsoleServerUrl() + "/rs/engine/deployment/" + encodedId + "/resume";
  }

  public String getDeleteDeploymentUrl(String id)
  {
    String encodedId = URL.encode(id);
    return config.getConsoleServerUrl() + "/rs/engine/deployment/" + encodedId + "/delete";
  }

  public String getJobsUrl()
  {
    return config.getConsoleServerUrl() + "/rs/engine/jobs";
  }

  public String getExecuteJobURL(String id)
  {
    String encodedId = URL.encode(id);
    return config.getConsoleServerUrl() + "/rs/engine/job/" + encodedId + "/execute";
  }

  public String getInstanceHistoryURL(String instanceId)
  {
    return config.getConsoleServerUrl() + "/rs/process/instance/history/" + instanceId;
  }

  public String getDefinitionHistoryURL(String definitionId)
  {
    return config.getConsoleServerUrl() + "/rs/process/definition/history/" + definitionId;
  }

  public String getProcessHistoryURL(String definitionId, String querystring) {
    String encodedId = URL.encode(definitionId);
    return config.getConsoleServerUrl() + "/rs/history/definition/" + encodedId + "/instances?" + querystring;
  }

  public String getProcessHistoryDefinitionsURL() {
    return config.getConsoleServerUrl() + "/rs/history/definitions";
  }

  public String getProcessHistoryInstanceKeysURL(String id) {
    String encodedId = URL.encode(id);
    return config.getConsoleServerUrl() + "/rs/history/definition/" + encodedId + "/instancekeys";
  }

  public String getProcessHistoryActivityKeysURL(String instanceId) {
    return config.getConsoleServerUrl() + "/rs/history/instance/" + instanceId + "/activities";
  }

  public String getProcessHistoryEventsURL(String instanceId) {
    return config.getConsoleServerUrl() + "/rs/history/instance/" + instanceId + "/events";
  }

  public String getProcessHistoryCompletedInstancesURL(String id, String timestamp, String timespan) {
    String encodedId = URL.encode(id);
    return config.getConsoleServerUrl() + "/rs/history/definition/" + encodedId + "/instances/completed?timestamp=" + timestamp + "&timespan=" + timespan;
  }

  public String getProcessHistoryFailedInstanceURL(String id, String timestamp, String timespan)
  {
    String encodedId = URL.encode(id);
    return config.getConsoleServerUrl() + "/rs/history/definition/" + encodedId + "/instances/failed?timestamp=" + timestamp + "&timespan=" + timespan;
  }

  public String getProcessHistoryTerminatedInstanceURL(String id, String timestamp, String timespan)
  {
    String encodedId = URL.encode(id);
    return config.getConsoleServerUrl() + "/rs/history/definition/" + encodedId + "/instances/terminated?timestamp=" + timestamp + "&timespan=" + timespan;
  }

  public String getProcessHistoryCompletedInstances4ChartURL(String id, String timespan)
  {
    String encodedId = URL.encode(id);
    return config.getConsoleServerUrl() + "/rs/history/definition/" + encodedId + "/instances/chart/completed?timespan=" + timespan;
  }

  public String getProcessHistoryFailedInstances4ChartURL(String id, String timespan)
  {
    String encodedId = URL.encode(id);
    return config.getConsoleServerUrl() + "/rs/history/definition/" + encodedId + "/instances/chart/failed?timespan=" + timespan;
  }

  public String getDefinitionHistoryNodeInfoURL(String definitionId, List<String> activities)
  {
    StringBuffer queryString = new StringBuffer();
    boolean isFirst = true;
    for (String activity : activities) {
      if (isFirst) {
        queryString.append("activity=" + activity);
        isFirst = false;
      } else {
        queryString.append("&activity=" + activity);
      }
    }

    return config.getConsoleServerUrl() + "/rs/process/definition/history/" + definitionId + "/nodeInfo?" + queryString.toString();
  }

  public static void main(String[] args) throws Exception
  {
    System.out.println(URL.encode("{http://www.jboss.org/bpel/examples}HelloGoodbye-1"));
  }
}