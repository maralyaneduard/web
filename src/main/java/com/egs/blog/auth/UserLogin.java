/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egs.blog.auth;

import com.egs.blog.backend.entities.User;
import com.egs.blog.backend.services.UserService;
import com.egs.blog.handlers.SessionContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author eduardm
 */
@ManagedBean(name = "userLogin")
@RequestScoped
public class UserLogin {

    @ManagedProperty("#{userService}")
    private UserService userService;
    @ManagedProperty("#{sessionContext}")
    private SessionContext sessionContext = null;

    private String email;
    private String password;

    public UserLogin() {
    }

    public String loginUser() {
        User user = userService.userLogin(email, password);
        if (user != null) {
            sessionContext.setUser(user);
            return "home";
        } else {
            return null;
        }
    }

    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
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

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
