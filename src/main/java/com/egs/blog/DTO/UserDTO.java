/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egs.blog.DTO;

import com.egs.blog.backend.entities.Post;
import com.egs.blog.backend.entities.User;
import com.egs.blog.backend.services.PostService;
import java.util.Date;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author eduardm
 */
public class UserDTO {
    
    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private Integer role;
    private Date registeredDate;
    
    public UserDTO() {
    }

    public UserDTO(Long id) {
        this.id = id;
    }

    public UserDTO(Long id, String firstname, String lastname, String username, String email, Integer role, Date registeredDate) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.role = role;
        this.registeredDate = registeredDate;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
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
        if (!(object instanceof User)) {
            return false;
        }
        UserDTO other = (UserDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.egs.blog.backend.entities.User[ id=" + id + " ]";
    }
    
    public static UserDTO getUserDTO(User user)
    {
        UserDTO userDto = new UserDTO(user.getId(),user.getFirstname(),user.getLastname(),user.getUsername(),user.getEmail(),user.getRole(),user.getRegisteredDate());
        return userDto;
    }
}
