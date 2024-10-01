package com.myplatform.myplatform.service;

import com.myplatform.myplatform.model.Page;
import com.myplatform.myplatform.repo.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageService {

    @Autowired
    private PageRepository pageRepository;

    public Page createPage(Integer workspaceId, Integer parentPageId, String title, String content) {
        Page page = new Page();
        page.setWorkspaceId(workspaceId);
        page.setParentPageId(parentPageId);
        page.setTitle(title);
        page.setContent(content);
        return pageRepository.save(page);
    }

    public List<Page> findPagesByWorkspaceId(Integer workspaceId) {
        return pageRepository.findByWorkspaceId(workspaceId);
    }

    public Page findPageById(Integer id) {
        return pageRepository.findById(id).orElse(null);
    }

    public Page updatePage(Integer id, String title, String content) {
        Page page = findPageById(id);
        if (page != null) {
            page.setTitle(title);
            page.setContent(content);
            return pageRepository.save(page);
        }
        return null;
    }

    public void deletePage(Integer id) {
        pageRepository.deleteById(id);
    }
}
