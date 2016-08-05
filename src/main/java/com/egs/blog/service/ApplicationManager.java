package com.egs.blog.service;

import com.egs.blog.backend.services.UserService;
import java.io.Serializable;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean(eager = false, name = "applicationManager")
@ApplicationScoped()
public class ApplicationManager implements Serializable {

    @ManagedProperty("#{userService}")
    private UserService userService = null;   

    public ApplicationManager() {
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
