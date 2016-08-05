/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egs.blog.web;

import com.egs.blog.backend.entities.Post;
import com.egs.blog.backend.services.PostService;
import com.egs.blog.backend.util.Status;
import com.egs.blog.handlers.SessionContext;
import com.egs.blog.utils.ParamUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author eduardm
 */
@ManagedBean(name = "postContentEditManagedBean")
@ViewScoped
public class PostContentEditManagedBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty("#{postService}")
    private PostService postService;

    private Post post = null;
    private Long postId;
    private Status[] statusList = null;
    private String status;

    public PostContentEditManagedBean() {
    }

    @PostConstruct
    public void init() {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("postId");
        if (id != null) {
            postId = ParamUtil.longValue(id);
            post = postService.getPost(postId);
        } else {
            post = new Post();
        }
    }

    public PostService getPostService() {
        return postService;
    }

    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Status[] getStatusList() {
        statusList = new Status[3];
        if (post.getStatus() != null) {
            for (int i = 0; i < Status.values().length; i++) {
                if (Status.values()[i] == post.getStatus()) {
                    statusList[0] = Status.values()[i];
                } else if (i == 0 || i == 1) {
                    if (statusList[1] == null) {
                        statusList[1] = Status.values()[i];
                    } else {
                        statusList[2] = Status.values()[i];
                    }
                } else {
                    statusList[i] = Status.values()[i];
                }
            }
        } else {
            for (int j = 0; j < Status.values().length; j++) {
                statusList[j] = Status.values()[j];
            }
        }
        return statusList;
    }

    public String saveChanges() {
        post.setStatus(Status.valueOf(status));
        postService.updatePost(post);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("postContentEdit.jsf?postId=" + post.getId());
        } catch (IOException ex) {
            Logger.getLogger(UserPostManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String addPost() {
        if (post != null) {
            post.setRating(0);
            post.setStatus(Status.valueOf(status));
            SessionContext context = (SessionContext) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionContext");
            post.setUser(context.getUser());
            postService.savePost(post);
        }
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("userPosts.jsf");
        } catch (IOException ex) {
            Logger.getLogger(UserPostManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
