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
import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author eduardm
 */
@ManagedBean(name = "postManagedBean")
@SessionScoped
public class PostManagedBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty("#{postService}")
    private PostService postService;
    @ManagedProperty("#{sessionContext}")
    private SessionContext sessionContext;
    private String title;
    private List<PostDTO> postList = null;

    public PostManagedBean() {
    }

    @PostConstruct
    public void init() {
        postList = new ArrayList<PostDTO>();
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

    public String search() {
        if (sessionContext.getUser() == null) {
            return null;
        }
        if (title != null) {
            List<Post> list = postService.getPostsByTitle(title);
            postList = new ArrayList<PostDTO>();
            for (Post p : list) {
                postList.add(PostDTO.getPostDTO(p));
            }
        }
        else
        {
            FacesMessage msg = new FacesMessage("message", "message");
            FacesContext.getCurrentInstance().addMessage("searchForm:search", msg);
        }
        return "posts";
    }

    public String searchLucky() {
        title="";
        if (sessionContext.getUser() == null) {
            return null;
        }
        List<Post> list = postService.getPostsByTitle(title);
        Random random = new Random();
        int randomPost = random.nextInt(list.size());
        postList = new ArrayList<PostDTO>();
        postList.add(PostDTO.getPostDTO(list.get(randomPost)));
        return "posts";
    }

    public SessionContext getSessionContext() {
        return sessionContext;
    }

    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    public void incrementRating(String id) {
        Post post = postService.getPostById(ParamUtil.longValue(id));
        if (post != null) {
            post.setRating(post.getRating() + 1);
            postService.updatePost(post);
        }
    }

    public void decrementRating(String id) {
        Post post = postService.getPostById(ParamUtil.longValue(id));
        if (post != null) {
            post.setRating(post.getRating() - 1);
            postService.updatePost(post);
        }
    }
}
