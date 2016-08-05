/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egs.blog.auth;

import com.egs.blog.handlers.SessionContext;
import java.io.IOException;
import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author eduardm
 */
@WebFilter("/portal/*")
public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String loginURL = req.getContextPath() + "/login.xhtml";
        boolean resourceRequest = req.getRequestURI().startsWith(req.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER + "/");
        if (session != null || !resourceRequest) {
            SessionContext sessionContext = (SessionContext) session.getAttribute("sessionContext");
            if (sessionContext.getUser() != null) {
                chain.doFilter(request, response); 
                return;
            }
        }
        res.sendRedirect(loginURL);
        
    }

    @Override
    public void destroy() {
    }

}
