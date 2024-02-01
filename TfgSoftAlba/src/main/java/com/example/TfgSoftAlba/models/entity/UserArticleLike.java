package com.example.TfgSoftAlba.models.entity;

import javax.persistence.*;

@Entity
@Table(name= "User_Article_like")
public class UserArticleLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "article_id")
    private Long articleId;
    
    @Column
    private boolean active;

    public UserArticleLike(){}

    public UserArticleLike(Long userId, Long articleId, Boolean active)
    {
        this.userId = userId;
        this.articleId = articleId;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }
    
    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
