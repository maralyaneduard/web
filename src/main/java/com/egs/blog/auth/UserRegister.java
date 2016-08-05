/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egs.blog.auth;

import com.egs.blog.backend.entities.User;
import com.egs.blog.backend.services.UserService;
import com.egs.blog.handlers.SessionContext;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author eduardm
 */
@ManagedBean(name = "userRegister")
@RequestScoped
public class UserRegister {

    @ManagedProperty("#{userService}")
    private UserService userService;
    @ManagedProperty("#{sessionContext}")
    private SessionContext sessionContext = null;

    private String userName;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Part file;

    public UserRegister() {
    }

    public String registerUser() {
        User user = new User();
        user.setUsername(userName);
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstname(firstName);
        user.setLastname(lastName);
        if (user.getImageNeme() == null) {
            user.setImageNeme("def.jpg");
        }

        InputStream input;
        try {
            input = file.getInputStream();
            byte[] image = IOUtils.toByteArray(input); // Apache commons IO.
            user.setImageData(image);
        } catch (IOException ex) {
            Logger.getLogger(UserRegister.class.getName()).log(Level.SEVERE, null, ex);
        }

        User registeredUser = userService.userRegister(user);
        if (user != null) {
            sessionContext.setUser(registeredUser);
            return "home";
        } else {
            return null;
        }
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public SessionContext getSessionContext() {
        return sessionContext;
    }

    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
}
