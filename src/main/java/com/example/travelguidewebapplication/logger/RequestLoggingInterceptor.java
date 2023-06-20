package com.example.travelguidewebapplication.logger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class RequestLoggingInterceptor implements HandlerInterceptor {

    private final CustomLogger customLogger;

    public RequestLoggingInterceptor(CustomLogger customLogger) {
        this.customLogger = customLogger;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String queryString = request.getQueryString();
        String parameters = getParametersAsString(request);
        String headers = getHeadersAsString(request);

        String logMessage = String.format("URL: %s, Method: %s, Query String: %s, Parameters: %s, Headers: %s",
                url, method, queryString, parameters, headers);
        customLogger.logRequest(logMessage);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // Burada herhangi bir işlem yapmanız gerekmiyorsa boş bırakabilirsiniz.
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // Burada herhangi bir işlem yapmanız gerekmiyorsa boş bırakabilirsiniz.
    }

    private String getParametersAsString(HttpServletRequest request) {
        StringBuilder parametersBuilder = new StringBuilder();
        request.getParameterMap().forEach((name, values) -> {
            for (String value : values) {
                parametersBuilder.append(name).append("=").append(value).append(", ");
            }
        });

        return parametersBuilder.toString();
    }

    private String getHeadersAsString(HttpServletRequest request) {
        StringBuilder headersBuilder = new StringBuilder();
        request.getHeaderNames().asIterator().forEachRemaining(name -> {
            String value = request.getHeader(name);
            headersBuilder.append(name).append("=").append(value).append(", ");
        });

        return headersBuilder.toString();
    }
}

