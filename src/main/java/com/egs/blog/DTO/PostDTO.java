/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egs.blog.DTO;

import com.egs.blog.backend.entities.Post;
import com.egs.blog.backend.entities.User;
import com.egs.blog.backend.util.Status;
import java.util.Date;

/**
 *
 * @author eduardm
 */
public class PostDTO {
  
    private Long id;
    private String title;
    private String contentPreview;
    private Date postedDate;
    private Status status;
    private int rating;
    private User user;
    
    public PostDTO() {
    }

    public PostDTO(Long id) {
        this.id = id;
    }

    public PostDTO(Long id, String title, String content, Date postedDate, Status status, int rating) {
        this.id = id;
        this.title = title;
        this.contentPreview = content;
        this.postedDate = postedDate;
        this.status = status;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return contentPreview;
    }

    public void setContent(String content) {
        this.contentPreview = content;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Post)) {
            return false;
        }
        PostDTO other = (PostDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.egs.blog.backend.entities.PostDTO[ id=" + id + " ]";
    }
    
    public static PostDTO getPostDTO(Post post)
    {
        PostDTO postDto = new PostDTO(post.getId(),post.getTitle(),
                null,post.getPostedDate(),post.getStatus(),
                post.getRating());
        postDto.contentPreview = (post.getContent().length() > 50)? post.getContent().substring(0, 50): post.getContent();
        return postDto;
    }
}
