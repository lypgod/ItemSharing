package com.lypgod.itemsharing.zuulserver.filter;

import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

/**
 * @author lypgod
 */
@Component
public class FilterUtil {
    public static final String CORRELATION_ID = "is-correlation-id";
    public static final String AUTH_TOKEN = "Authorization";
    public static final String USER_ID = "is-user-id";
    public static final String FILTER_TYPE_PRE = "pre";
    public static final String FILTER_TYPE_POST = "post";
    public static final String FILTER_TYPE_ROUTE = "route";

    public String getCorrelationId() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        return currentContext.getRequest().getHeader(CORRELATION_ID) != null ?
                currentContext.getRequest().getHeader(CORRELATION_ID) :
                currentContext.getZuulRequestHeaders().get(CORRELATION_ID);
    }

    public void setCorrelationId(String correlationId) {
        RequestContext.getCurrentContext().addZuulRequestHeader(CORRELATION_ID, correlationId);
    }

    public String getAuthToken() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        return currentContext.getRequest().getHeader(AUTH_TOKEN) != null ?
                currentContext.getRequest().getHeader(AUTH_TOKEN) :
                currentContext.getZuulRequestHeaders().get(AUTH_TOKEN);
    }

    public void setAuthToken(String authToken) {
        RequestContext.getCurrentContext().addZuulRequestHeader(AUTH_TOKEN, authToken);
    }

    public String getUserId() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        return currentContext.getRequest().getHeader(USER_ID) != null ?
                currentContext.getRequest().getHeader(USER_ID) :
                currentContext.getZuulRequestHeaders().get(USER_ID);
    }

    public void setUserId(String userId) {
        RequestContext.getCurrentContext().addZuulRequestHeader(USER_ID, userId);
    }
}
