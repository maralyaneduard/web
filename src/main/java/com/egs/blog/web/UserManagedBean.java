/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egs.blog.web;

import com.egs.blog.backend.entities.User;
import com.egs.blog.backend.services.UserService;
import com.egs.blog.handlers.SessionContext;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
/**
 *
 * @author eduardm
 */
@ManagedBean(name = "userManagedBean")
@ViewScoped
public class UserManagedBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty("#{userService}")
    private UserService userService;
    @ManagedProperty("#{sessionContext}")
    private SessionContext sessionContext = null;
    private User user = null;
    
    public UserManagedBean() {
    }

    @PostConstruct
    public void init() {
        user = userService.getUser(sessionContext.getUser().getId());
        System.out.println("User lastname POST " + user.getLastname());
        if (user == null) {
            //redirect/ error page
        }
    }

    public List<User> getUserList() {
        return userService.getUserList(0, 1000);
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String logoutUser() {
        sessionContext.setUser(null);
        return "logout";
    }

    public String deleteUser() {
        userService.deleteUser(sessionContext.getUser().getId());
        return logoutUser();
    }

    public String updateUser() {
        user.setImageNeme("def.jpg");
        userService.updateUser(user);
        sessionContext.setUser(user);
        return "userInfo";
    }
}
