/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egs.blog.web;

import com.egs.blog.DTO.PostDTO;
import com.egs.blog.backend.entities.Post;
import com.egs.blog.backend.services.PostService;
import com.egs.blog.handlers.SessionContext;
import com.egs.blog.utils.ParamUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author eduardm
 */
@ManagedBean(name = "userPostManagedBean")
@ViewScoped
public class UserPostManagedBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty("#{postService}")
    private PostService postService;
    @ManagedProperty("#{sessionContext}")
    private SessionContext sessionContext;
    private String title;
    private List<PostDTO> postList = null;

    public UserPostManagedBean() {
    }

    @PostConstruct
    public void init() {
        List<Post> list = postService.getPostsByUserId(sessionContext.getUser().getId());
        postList = new ArrayList<PostDTO>();
        for (Post p : list) {
            postList.add(PostDTO.getPostDTO(p));
        }
    }

    public PostService getPostService() {
        return postService;
    }

    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<PostDTO> getPostList() {
        return postList;
    }

    public void setPostList(List<PostDTO> postList) {
        this.postList = postList;
    }

    public SessionContext getSessionContext() {
        return sessionContext;
    }

    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }
    
    public String delete(String postId)
    {
        Long id = ParamUtil.longValue(postId);
        postService.deletePost(id);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("userPosts.jsf");
        } catch (IOException ex) {
            Logger.getLogger(UserPostManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
