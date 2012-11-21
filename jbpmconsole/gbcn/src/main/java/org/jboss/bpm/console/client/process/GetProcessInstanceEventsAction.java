package org.jboss.bpm.console.client.process;

import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.Response;
import com.mvc4g.client.Controller;
import java.util.List;
import org.jboss.bpm.console.client.URLBuilder;
import org.jboss.bpm.console.client.common.AbstractRESTAction;
import org.jboss.bpm.console.client.model.JSOParser;
import org.jboss.bpm.console.client.model.StringRef;

public class GetProcessInstanceEventsAction extends AbstractRESTAction
{
  public static final String ID = GetProcessInstanceEventsAction.class.getName();

  public String getId()
  {
    return ID;
  }

  public String getUrl(Object event)
  {
    String instanceId = (String)event;
    return URLBuilder.getInstance().getProcessHistoryEventsURL(instanceId);
  }

  public RequestBuilder.Method getRequestMethod()
  {
    return RequestBuilder.GET;
  }

  public void handleSuccessfulResponse(Controller controller, Object event, Response response)
  {
    InstanceDetailView view = (InstanceDetailView)controller.getView(InstanceDetailView.ID);
    List<StringRef> refs = JSOParser.parseStringRef(response.getText());

    view.populateProcessInstanceEvents(refs);
  }
}