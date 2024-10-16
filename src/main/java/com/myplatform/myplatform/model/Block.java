package com.myplatform.myplatform.model;

import javax.persistence.*;

@Entity
@Table(name = "blocks")
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "block_seq")
    @SequenceGenerator(name = "block_seq", sequenceName = "block_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "page_id", nullable = false)
    private Integer pageId;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "content", nullable = false)
    private String content;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPageId() {
        return pageId;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
