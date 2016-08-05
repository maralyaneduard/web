/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egs.blog.handlers;

import com.egs.blog.backend.entities.User;
import com.egs.blog.backend.services.UserService;
import com.egs.blog.service.ApplicationManager;
import com.egs.blog.utils.FacesUtil;
import com.egs.blog.utils.ParamUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.SingleThreadModel;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author eduardm
 */
@WebServlet(urlPatterns = {"/ImagePreview"})
public class ImagePreview extends HttpServlet implements SingleThreadModel {

    private static final long serialVersionUID = -6624464650990859671L;
    
    @Override
    public void init() throws ServletException {
        super.init();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPreviewImage(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    private void doPreviewImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        FacesContext context = FacesUtil.getFacesContext(request, response);
        ApplicationManager appManager = context.getApplication().evaluateExpressionGet(
                context, "#{applicationManager}", ApplicationManager.class);
        
        String userId = request.getParameter("userId");

        if (userId != null) {
            User user = appManager.getUserService().getUserById(ParamUtil.longValue(userId));
            if (user != null) {
                byte[] content = user.getImageData();
                if (content != null) {
                    response.addHeader("Pragma", "cache");
                    response.addHeader("Cache-Control", "max-age=3600, must-revalidate");
                    response.addDateHeader("Expires", System.currentTimeMillis() + 1000 * 3600 * 10);
                    response.setContentType("image/png");
                    try {
                        response.getOutputStream().write(content);
                    } catch (IOException e) {

                    } catch (Exception e) {

                    } finally {
                        content = null;
                        user = null;
                    }
                    return;
                }
            } else {
                response.addHeader("Pragma", "no-cache");
                response.addDateHeader("Expires", System.currentTimeMillis() - 1000 * 3600);
                try {
                    response.getWriter().println("file object is null");
                } catch (Exception e) {
                }
                return;
            }
        }
        response.addHeader("Pragma", "no-cache");
        response.addDateHeader("Expires", System.currentTimeMillis() - 1000 * 3600);
        try {
            response.getWriter().println("file id is not set");
        } catch (Exception e) {
        }
    }
}
