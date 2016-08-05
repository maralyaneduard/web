/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egs.blog.web;

import com.egs.blog.DTO.UserDTO;
import com.egs.blog.backend.entities.User;
import com.egs.blog.backend.services.PostService;
import com.egs.blog.backend.services.UserService;
import com.egs.blog.handlers.SessionContext;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author eduardm
 */
@ManagedBean(name = "userInfoManagedBean")
@ViewScoped
public class UserInfoManagedBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty("#{userService}")
    private UserService userService;
    @ManagedProperty("#{postService}")
    private PostService postService;
    @ManagedProperty("#{sessionContext}")
    private SessionContext sessionContext = null;
    
    private UserDTO userDto = null;
    private User user = null;
    private int postCount;
    
    
    public UserInfoManagedBean() {
    }

    @PostConstruct
    public void init() {
        user = userService.getUser(sessionContext.getUser().getId());
        System.out.println("User lastname POST " + user.getLastname());
        if (user != null) {
            postCount = postService.getPostsByUserId(user.getId()).size();
            userDto = UserDTO.getUserDTO(user);
        }
    }

    public PostService getPostService() {
        return postService;
    }

    public void setPostService(PostService postService) {
        this.postService = postService;
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

    public UserDTO getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDTO userDto) {
        this.userDto = userDto;
    }

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
