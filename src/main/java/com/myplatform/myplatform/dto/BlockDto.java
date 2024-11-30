package com.myplatform.myplatform.dto;

public class BlockDto {
    private String type;
    private String content;
    private String title;
    private Integer frontendId;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getFrontendId() {
        return frontendId;
    }

    public void setFrontendId(Integer frontendId) {
        this.frontendId = frontendId;
    }
}
