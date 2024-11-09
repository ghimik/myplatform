package com.myplatform.myplatform.dto;

import java.util.List;

public class PageDto {
    private String title;
    private List<BlockDto> pageBlocks;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<BlockDto> getPageBlocks() {
        return pageBlocks;
    }

    public void setPageBlocks(List<BlockDto> pageBlocks) {
        this.pageBlocks = pageBlocks;
    }
}
