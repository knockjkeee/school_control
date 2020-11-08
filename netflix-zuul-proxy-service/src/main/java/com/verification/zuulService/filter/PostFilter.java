package com.verification.zuulService.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.FORWARD_TO_KEY;


public class PostFilter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(PostFilter.class);
    private static String url = "http://172.18.136.46/api/view";


    @Override
    public String filterType() {
        return "post";
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
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        log.error(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
        log.error("Status code " + context.getResponseStatusCode());
        int statusCode = context.getResponseStatusCode();

        if (statusCode >= 400) {
            try {
                context.setSendZuulResponse(false);
                context.put(FORWARD_TO_KEY, url);
                context.setResponseStatusCode(200);
                context.getResponse().sendRedirect(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



//        try {
//            ctx.setSendZuulResponse(false);
//            ctx.put(FORWARD_TO_KEY, redirectURL);
//            ctx.setResponseStatusCode(HttpStatus.SC_TEMPORARY_REDIRECT);
//            ctx.getResponse().sendRedirect(redirectURL);
//        } catch (IOException e) {
//            log.error("unable to send a redirect to the login page");
//        }



        return null;
    }
}
