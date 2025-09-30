package org.example.springdemo.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.logging.Logger;

public class AuthenticationLoggingFilter implements Filter {
    private final Logger logger = Logger.getLogger(AuthenticationLoggingFilter.class.getName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        var httpRequest = (HttpServletRequest) request;
        var requestId = httpRequest.getHeader("Request-Id");

        logger.info("Successfully authenticated request with id " + requestId);
        
        try {
            filterChain.doFilter(request, response);
        } catch (IOException e) {
            logger.severe("IO error occurred during request processing for request id " + requestId + ": " + e.getMessage());
            throw e;
        } catch (ServletException e) {
            logger.severe("Servlet error occurred during request processing for request id " + requestId + ": " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.severe("Unexpected error occurred during request processing for request id " + requestId + ": " + e.getMessage());
            throw new ServletException(e);
        }
    }
}