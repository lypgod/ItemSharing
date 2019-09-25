package com.lypgod.itemsharing.zuulserver.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author lypgod
 */
@Log4j2
@Component
public class ResponseFilter extends ZuulFilter {
    @Resource
    private FilterUtil filterUtil;

    @Override
    public String filterType() {
        return FilterUtil.FILTER_TYPE_POST;
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        log.warn("ResponseFilter is adding Correlation-Id to the outbound headers: {}", filterUtil.getCorrelationId());
        currentContext.getResponse().addHeader(FilterUtil.CORRELATION_ID, filterUtil.getCorrelationId());
        log.warn("ResponseFilter completing outgoing request for {}", currentContext.getRequest().getRequestURI());
        return null;
    }
}
