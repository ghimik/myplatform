package com.myplatform.myplatform.model;

import javax.persistence.*;

@Entity
@Table(name = "pages")
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "page_seq")
    @SequenceGenerator(name = "page_seq", sequenceName = "page_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "workspace_id", nullable = false)
    private Integer workspaceId;

    @Column(name = "parent_page_id")
    private Integer parentPageId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(Integer workspaceId) {
        this.workspaceId = workspaceId;
    }

    public Integer getParentPageId() {
        return parentPageId;
    }

    public void setParentPageId(Integer parentPageId) {
        this.parentPageId = parentPageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}