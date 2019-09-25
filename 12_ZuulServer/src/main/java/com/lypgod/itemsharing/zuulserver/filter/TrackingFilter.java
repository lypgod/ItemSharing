package com.lypgod.itemsharing.zuulserver.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author lypgod
 */
@Log4j2
@Component
public class TrackingFilter extends ZuulFilter {
    @Resource
    private FilterUtil filterUtil;

    @Override
    public String filterType() {
        return FilterUtil.FILTER_TYPE_PRE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        if (isCorrelationIdPresent()) {
            log.warn("TrackingFilter found Correlation-Id: {}", filterUtil.getCorrelationId());
        } else {
            filterUtil.setCorrelationId(generateCorrelationId());
            log.warn("TrackingFilter generated new Correlation-Id: {}", filterUtil.getCorrelationId());
        }

        log.warn("TrackingFilter is processing incoming request for {}", RequestContext.getCurrentContext().getRequest().getRequestURI());

        return null;
    }

    private boolean isCorrelationIdPresent() {
        return filterUtil.getCorrelationId() != null;
    }

    private String generateCorrelationId() {
        return UUID.randomUUID().toString();
    }
}
