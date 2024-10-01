package com.myplatform.myplatform.dto;

public class PageDto {
    private Integer workspaceId;
    private Integer parentPageId;
    private String title;
    private String content;

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
