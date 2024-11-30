package com.myplatform.myplatform.dto;

import com.myplatform.myplatform.model.Page;

import java.util.List;

public class PagesDto {

    private List<PageDto> pages;

    public List<PageDto> getPages() {
        return pages;
    }

    public void setPages(List<PageDto> pages) {
        this.pages = pages;
    }
}
