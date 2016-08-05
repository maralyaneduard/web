/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egs.blog.web;

import com.egs.blog.backend.entities.Post;
import com.egs.blog.backend.services.PostService;
import com.egs.blog.utils.ParamUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author eduardm
 */
@ManagedBean(name = "postContentManagedBean")
@RequestScoped
public class PostContentManagedBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty("#{postService}")
    private PostService postService;
    
    private Post post = null;
    private Long postId;
    
    public PostContentManagedBean() {
    }

    @PostConstruct
    public void init() {
        postId = ParamUtil.longValue(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("postId"));
        post = postService.getPost(postId);
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
}
